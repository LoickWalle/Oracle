package org.example;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                if(atomicInteger.get() < 10 ){
                    System.out.println(Thread.currentThread().getName() + " a incrémenté le compteur à " + atomicInteger.incrementAndGet());
                }
            });
        }
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
        System.out.println("Valeur finale du compteur : " + atomicInteger.get());
    }
}