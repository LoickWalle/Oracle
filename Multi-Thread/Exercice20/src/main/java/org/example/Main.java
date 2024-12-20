package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int NB_THREAD = 1_000;

    public static void main(String[] args) throws InterruptedException {
        double nativeTime;
        double virtualTime;

        nativeTime = getPerformanceTime(Executors.newFixedThreadPool(NB_THREAD), "natif");
        virtualTime = getPerformanceTime(Executors.newVirtualThreadPerTaskExecutor(), "virtuel");

        System.out.println("Difference time : " + Math.abs(nativeTime - virtualTime) + " ms");
    }

    private static double getPerformanceTime(ExecutorService executor, String type) {
        long start = System.currentTimeMillis();
        try (executor){
            for (int i = 0; i < NB_THREAD; i++) {
                executor.execute(task());
            }
        }
        long end = System.currentTimeMillis();
        double virtualTime = end - start;
        System.out.println("Execution time ("+type+" threads) : " + virtualTime);
        return virtualTime;
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