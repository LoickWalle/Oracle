package org.example;

import org.example.models.Printer;
import org.example.threads.Task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Lock reentrantLock = new ReentrantLock();
    private static final Printer printer = new Printer();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = createThreads(new Task(printer, reentrantLock), 3);

        runThreads(threads);
        System.out.println("Toutes les tâches sont terminées.");
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