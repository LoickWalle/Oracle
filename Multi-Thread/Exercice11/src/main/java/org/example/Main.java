package org.example;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Object lock = new Object();
    private static final Lock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        demoWithoutSync();
        demoWithLock();
        demoWithReentrantLock();
    }

    static class IdGenerator{
        private static int id =0;

        public static int generateId(){
            return id++;
        }
    }

    private static void demoWithReentrantLock() throws InterruptedException {
        IdGenerator.id = 0;
        Thread[] threads = createThreads(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    reentrantLock.lock();
                    IdGenerator.generateId();
                } finally {
                    reentrantLock.unlock();
                }
            }
        }, 5);

        runThreads(threads);
        System.out.println("Valeur finale with reentrant sync : " + IdGenerator.id);
    }

    private static void demoWithLock() throws InterruptedException {
        IdGenerator.id = 0;
        Thread[] threads = createThreads(() -> {
            for (int i = 0; i < 1000; i++) {
                synchronized (lock){
                    IdGenerator.generateId();
                }
            }
        }, 5);

        runThreads(threads);
        System.out.println("Valeur finale with sync : " + IdGenerator.id);
    }

    private static void demoWithoutSync() throws InterruptedException {
        IdGenerator.id = 0;
        Thread[] threads = createThreads(() -> {
            for (int i = 0; i < 1000; i++) {
                IdGenerator.generateId();
            }
        }, 5);

        runThreads(threads);
        System.out.println("Valeur finale without sync : " + IdGenerator.id);
    }

    private static Thread[] createThreads(Runnable task, int nbThread){
        Thread[] threads = new Thread[nbThread];
        for (int i = 0; i < nbThread; i++) {
            threads[i] = new Thread(task);
        }
        return threads;
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}