package org.example;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NB_THREAD = 1_000;

    public static void main(String[] args) throws InterruptedException {
        long start;
        long end;

        start = System.nanoTime();
        try (ExecutorService executor = Executors.newFixedThreadPool(NB_THREAD)){
            for (int i = 0; i < NB_THREAD; i++) {
                executor.execute(task());
            }
        }
        end = System.nanoTime();

        System.out.println("Execution time (natif threads) : " + (end - start));
        start = System.nanoTime();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < NB_THREAD; i++) {
                executor.execute(task());
            }
        }
        end = Instant.now().getNano();

        System.out.println("Execution time (virtual threads) : " + (end - start));
    }

    private static Runnable task() {
        return () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}