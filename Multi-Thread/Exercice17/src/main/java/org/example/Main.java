package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> shops = new ConcurrentHashMap<>();

        shops.put("ProductA", 20);
        shops.put("ProductB", 20);
        shops.put("ProductC", 20);

        Runnable buyTask = () -> {
            for (int i = 0; i < 10; i++) {
                String productKey = pickRandomProductKey();
                System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de " + productKey);
                shops.put(productKey, shops.get(productKey)-1);
            }
        };

        Runnable restockTask = () -> {
            for (int i = 0; i < 10; i++) {
                String productKey = pickRandomProductKey();
                System.out.println(Thread.currentThread().getName() + " a réapprovisionné 10 unités de " + productKey);
                shops.put(productKey, shops.get(productKey)+10);
            }
        };

        Thread thread = new Thread(buyTask, "Acheteur-1");
        Thread thread2 = new Thread(buyTask, "Acheteur-2");
        Thread thread3 = new Thread(restockTask, "Réapprovisionneur");

        Thread[] threads = new Thread[]{thread, thread2, thread3};
        runThreads(threads);

        System.out.println("Inventaire final : " + shops);
    }

    private static String pickRandomProductKey() {
        int productPick = ThreadLocalRandom.current().nextInt(3);
        String productKey = "";
        switch (productPick){
            case 0 -> productKey = "ProductA";
            case 1 -> productKey = "ProductB";
            case 2 -> productKey = "ProductC";
        }
        return productKey;
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}