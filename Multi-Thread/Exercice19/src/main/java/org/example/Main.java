package org.example;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    private static final int NB_ELEMENTS = 10;
    private static final ConcurrentLinkedQueue<String> elements = new ConcurrentLinkedQueue<>();
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable addTask = () -> addElements();
        Runnable removeTask = () -> removeElements();

        executor.submit(addTask);
        executor.submit(removeTask);

        executor.shutdown();

        if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }

        System.out.println("État final de la file : " + elements);
    }

    private static void addElements() {
        Thread.currentThread().setName("Producer");
        for (int i = 0; i < NB_ELEMENTS; i++) {
            String element = Thread.currentThread().getName() + "-Element-"+i;
            System.out.println(Thread.currentThread().getName() + " a ajouté : " + element);
            elements.add(element);
        }
    }

    private static void removeElements() {
        Thread.currentThread().setName("Consumer");
        delay();
        for (int i = 0; i < NB_ELEMENTS; i++) {
            if(elements.isEmpty())
                System.out.println(Thread.currentThread().getName() + " n'a trouvé aucun élément à retirer.");
            else{
                String elementPick = elements.poll();
                System.out.println(Thread.currentThread().getName() + " a retiré : " + elementPick);
            }
        }
    }

    private static void delay() {
        if (random.nextBoolean()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}