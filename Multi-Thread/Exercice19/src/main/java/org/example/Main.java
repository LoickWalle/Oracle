package org.example;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<String> elements = new ConcurrentLinkedQueue<>();

        Runnable addTask = () -> {
            for (int i = 0; i < 10; i++) {
                String element = Thread.currentThread().getName() + "-Element-"+i;
                System.out.println(Thread.currentThread().getName() + " a ajouté : " + element);
                elements.add(element);
            }
        };

        Runnable removeTask = () -> {
            Random random = new Random();
            if (random.nextBoolean()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            for (int i = 0; i < 10; i++) {
                if(elements.isEmpty())
                    System.out.println(Thread.currentThread().getName() + " n'a trouvé aucun élément à retirer.");
                else{
                    String elementPick = elements.poll();
                    System.out.println(Thread.currentThread().getName() + " a retiré : " + elementPick);
                }
            }
        };

        Thread thread = new Thread(addTask, "Producer");
        Thread thread2 = new Thread(removeTask, "Consumer");

        Thread[] threads = new Thread[]{thread, thread2};
        runThreads(threads);

        System.out.println("État final de la file : " + elements);
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}