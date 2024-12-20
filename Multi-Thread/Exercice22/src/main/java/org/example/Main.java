package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            CompletableFuture<Void> step1 = CompletableFuture.runAsync(() -> {
                delay();
                System.out.println("Étape 1 : Lecture des données");
            }, executor);

            CompletableFuture<Void> step2 = step1.thenRunAsync(() -> {
                delay();
                System.out.println("Étape 2 : Traitement des données");
            }, executor);

            CompletableFuture<Void> step3 = step2.thenRunAsync(() -> {
                delay();
                System.out.println("Étape 3 : Stockage des données");
            }, executor);

            step3.join();
        }

        System.out.println("Toutes les étapes ont été effectué.");
    }

    private static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}