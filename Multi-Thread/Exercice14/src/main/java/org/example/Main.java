package org.example;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3); // 3 threads

        Thread[] threads = new Thread[3];

        Runnable task = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + " commence à charger les données.");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " a terminé le chargement des données.");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        threads[0] = new Thread(task);
        threads[1] = new Thread(task);
        threads[2] = new Thread(task);
        runThreads(threads);
        System.out.println("Fusion des données terminée. Tous les threads peuvent continuer. ");
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }
}
