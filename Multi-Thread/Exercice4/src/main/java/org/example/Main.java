package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        executorServiceWithRunnable();
//        threadPool();
        scheduledExecutorService();
    }

    public static void executorServiceWithRunnable() throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> System.out.println("Task with runnable"));
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

    }

    public static void threadPool(){
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executor.submit(()-> System.out.println("Task n°"+ taskId +" executer par " + Thread.currentThread().getName()));
        }
        executor.shutdown();
    }

    public static void scheduledExecutorService() throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        scheduler.schedule(() -> System.out.println("Task executed after 3 sec"),3, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> System.out.println("Task executed every 2 sec"), 1,2, TimeUnit.SECONDS);

        Thread.sleep(7000);
        scheduler.shutdown();
    }
}