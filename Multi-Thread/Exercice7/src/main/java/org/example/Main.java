package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        scheduler.scheduleWithFixedDelay(() -> {
            System.out.println("Message périodique " + atomicInteger.incrementAndGet());
            if(atomicInteger.get() >= 5){
                scheduler.shutdown();
                System.out.println("Programme terminé.");
            }
        }, 1, 2, TimeUnit.SECONDS);
    }
}