package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executor.submit(()-> System.out.println("Résultat de la tâche "+ taskId +" : " + taskId * taskId));
        }
        executor.shutdown();
    }
}