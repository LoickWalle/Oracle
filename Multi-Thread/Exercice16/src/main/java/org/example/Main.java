package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int nbThread = 4;

        int[] numbers = new int[]{0,1,2,3,4,5,6,7};
        //List<Integer> partials = Collections.synchronizedList(new ArrayList<>());
        AtomicIntegerArray partials = new AtomicIntegerArray(nbThread);

        CyclicBarrier barrier = new CyclicBarrier(nbThread, new Thread(() -> {
            //System.out.println("Somme totale : " + partials.stream().mapToInt(value -> value).sum());
            int totalSum = 0;
            for (int i = 0; i < nbThread; i++) {
                totalSum += partials.get(i);
            }
            System.out.println("Somme totale : " + totalSum);
        }));
        Thread[] threads = new Thread[4];

        for (int i = 0; i < nbThread; i++) {
            int start = i*2;
            int end = (i*2)+1;
            int finalI = i;
            threads[i] = new Thread(() -> {
                try {
                    int result = numbers[start] + numbers[end];
                    //partials.add(result);
                    partials.set(finalI, result);

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
