package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        SharedResource sharedResource = new SharedResource();

        Thread[] threads = new Thread[2];

        threads[0] = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock){
                    sharedResource.add(i);
                }
            }
        });

        threads[1] = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock){
                    sharedResource.remove();
                }
            }
        });

        runThreads(threads);
        System.out.println("État final de la liste : " + SharedResource.getList());
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}