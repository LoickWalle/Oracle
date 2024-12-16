package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executor.submit(()-> System.out.println("Résultat de la tâche "+ taskId +" : " + taskId * taskId));
        }
        executorService.shutdown();
    }
}