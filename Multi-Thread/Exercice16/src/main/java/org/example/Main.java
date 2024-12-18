package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int nbThread = 4;

        int[] numbers = new int[]{0,1,2,3,4,5,6,7};
        List<Integer> partials = new ArrayList<>();

        CyclicBarrier barrier = new CyclicBarrier(nbThread, new Thread(() -> {
            System.out.println("Somme totale : " + partials.stream().mapToInt(value -> value).sum());
        }));
        Thread[] threads = new Thread[4];

        for (int i = 0; i < nbThread; i++) {
            int start = i*2;
            int end = (i*2)+1;
            threads[i] = new Thread(() -> {
                try {
                    int result = numbers[start] + numbers[end];
                    partials.add(result);
                    System.out.println(Thread.currentThread().getName() + "a calculé une somme partielle de (indice : "+ start +" à indice : "+end+") : "+result);
                    barrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        runThreads(threads);
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }
}
