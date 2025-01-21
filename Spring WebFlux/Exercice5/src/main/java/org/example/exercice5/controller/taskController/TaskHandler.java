package org.example.exercice5.controller.taskController;

import org.example.exercice5.entity.Task;
import org.example.exercice5.service.TaskService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class TaskHandler {

    private final TaskService taskService;

    public TaskHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    public Mono<ServerResponse> getAllTasks(ServerRequest request) {
        return ServerResponse.ok().body(taskService.getAllTasks(), Task.class);
    }

    public Mono<ServerResponse> getTaskByID(ServerRequest request) {
        return taskService.getTaskById(UUID.fromString(request.pathVariable("id")))
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createTask(ServerRequest request) {
        return request.bodyToMono(Task.class)
                .flatMap(taskService::addTask)
                .flatMap(task -> ServerResponse.created(request.uri()).bodyValue(task));
    }

    public Mono<ServerResponse> updateTask(ServerRequest request) {
        return request.bodyToMono(Task.class)
                .flatMap(task -> taskService.updateTask(UUID.fromString(request.pathVariable("id")), task))
                .flatMap(task -> ServerResponse.accepted().bodyValue(task));
    }

    public Mono<ServerResponse> deleteTask(ServerRequest request) {
        UUID taskId = UUID.fromString(request.pathVariable("id"));
        return taskService.deleteTask(taskId) ? ServerResponse.ok().bodyValue(true) : ServerResponse.notFound().build();
    }
}
