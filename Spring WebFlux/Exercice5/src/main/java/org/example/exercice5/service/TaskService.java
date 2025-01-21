package org.example.exercice5.service;

import org.example.exercice5.entity.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.UUID;

@Service
public class TaskService {
    HashMap<UUID, Task> tasks = new HashMap<>();

    public TaskService() {
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        tasks.put(uuid, new Task(uuid, "une tache", true));
        tasks.put(uuid2, new Task(uuid2, "une tache2", false));
        tasks.put(uuid3, new Task(uuid3, "une tache3", true));
    }

    public Flux<Task> getAllTasks(){
        return Flux.fromIterable(tasks.values());
    }

    public Mono<Task> getTaskById(UUID id){
        return Mono.justOrEmpty(tasks.get(id));
    }

    public Mono<Task> addTask(Task task){
        task.setId(UUID.randomUUID());
        tasks.put(task.getId(), task);
        return Mono.just(task);
    }

    public Mono<Task> updateTask(UUID id, Task task){
        tasks.put(id, task);
        return Mono.just(tasks.get(id));
    }

    public boolean deleteTask(UUID id){
        if(tasks.containsKey(id)){
            tasks.remove(id);
            return true;
        }
        return false;
    }
}
