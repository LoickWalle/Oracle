package org.example;

import org.example.models.CalculateurCallable;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 1; i <= 5; i++) {
            Callable<Integer> callable = new CalculateurCallable(i);
            Future<Integer> result = executorService.submit(callable);
            System.out.println("Le cube de "+i+" est " +result.get());
        }

        executorService.shutdown();
    }
}