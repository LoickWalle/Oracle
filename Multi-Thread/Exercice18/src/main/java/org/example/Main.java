package org.example;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> products = new CopyOnWriteArrayList<>();

        Runnable task = () -> {
            for (int i = 0; i < 10; i++) {
                products.add(Thread.currentThread().getName()+"-Produit-"+i);
            }
        };

        Thread thread = new Thread(task);
        Thread thread2 = new Thread(task);

        Thread[] threads = new Thread[]{thread, thread2};
        runThreads(threads);

        System.out.println("Liste finale des produits : " + products);
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}