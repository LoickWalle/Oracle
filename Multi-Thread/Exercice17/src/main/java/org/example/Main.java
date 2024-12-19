package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> shops = new ConcurrentHashMap<>();

        shops.put("ProductA", 20);
        shops.put("ProductB", 20);
        shops.put("ProductC", 20);

//        Runnable buyTask = () -> {
//            for (int i = 0; i < 10; i++) {
//                int productPick = (int) Math.ceil(Math.random()*3);
//                switch (productPick){
//                    case 1 :
//                        System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de ProduitA");
//                        shops.put("ProductA", shops.get("ProductA")-1);
//                        break;
//                    case 2 :
//                        System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de ProduitB");
//                        shops.put("ProductB", shops.get("ProductB")-1);
//                        break;
//                    case 3 :
//                        System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de ProduitC");
//                        shops.put("ProductC", shops.get("ProductC")-1);
//                        break;
//                }
//            }
//        };

        Runnable buyTask = () -> {
            for (int i = 0; i < 10; i++) {
                int productPick = (int) Math.ceil(Math.random()*3);
                String productKey = "";
                switch (productPick){
                    case 1 -> productKey = "ProductA";
                    case 2 -> productKey = "ProductB";
                    case 3 -> productKey = "ProductC";
                }
                System.out.println(Thread.currentThread().getName() + " a acheté 1 unité de " + productKey);
                shops.put(productKey, shops.get(productKey)-1);
            }
        };

        Runnable restockTask = () -> {
            for (int i = 0; i < 10; i++) {
                int productPick = (int) Math.ceil(Math.random()*3);
                switch (productPick){
                    case 1 :
                        System.out.println(Thread.currentThread().getName() + " a réapprovisionné 10 unités de ProduitA");
                        shops.put("ProductA", 10 + shops.get("ProductA"));
                        break;
                    case 2 :
                        System.out.println(Thread.currentThread().getName() + " a réapprovisionné 10 unités de ProduitB");
                        shops.put("ProductB", 10 + shops.get("ProductB"));
                        break;
                    case 3 :
                        System.out.println(Thread.currentThread().getName() + " a réapprovisionné 10 unités de ProduitC");
                        shops.put("ProductC", 10 + shops.get("ProductC"));
                        break;
                }
            }
        };

        Thread thread = new Thread(buyTask, "Acheteur-1");
        Thread thread2 = new Thread(buyTask, "Acheteur-2");
        Thread thread3 = new Thread(restockTask, "Réapprovisionneur");

        Thread[] threads = new Thread[]{thread, thread2, thread3};
        runThreads(threads);

        System.out.println("Inventaire final : " + shops);
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }

}