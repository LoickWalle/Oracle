package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.schedule(() -> System.out.println("Task 1 executed after 1 sec"),1, TimeUnit.SECONDS);
        scheduler.schedule(() -> System.out.println("Task 2 executed after 2 sec"),2, TimeUnit.SECONDS);
        scheduler.schedule(() -> System.out.println("Task 3 executed after 3 sec"),3, TimeUnit.SECONDS);


        Thread.sleep(4000);
        scheduler.shutdown();
        System.out.println("Toutes les tâches ont été exécutées. Arrêt du programme.");
    }
}