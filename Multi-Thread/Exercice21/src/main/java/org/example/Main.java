package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    private static final int NB_THREAD = 1_000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<String> allAnswers = new ArrayList<>();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()){
            CompletionService<String> completionService = new ExecutorCompletionService<>(executor);

            for (int i = 0; i < NB_THREAD; i++) {
                completionService.submit(task());
            }

            for (int i = 0; i < NB_THREAD; i++) {
                String result = completionService.take().get();
                allAnswers.add(result);
            }
        }

        System.out.println("Toutes les connections ont répondus.");
        System.out.println("Total de réponses : " + allAnswers.size());
    }

    private static Callable<String> task() {
        return () -> {
            try {
                Thread.sleep(2000);
                return "Connexion établie !";
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }
}