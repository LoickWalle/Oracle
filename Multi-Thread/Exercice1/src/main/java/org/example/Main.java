package org.example;

import org.example.models.MyRunnable;
import org.example.models.MyThread;
import org.example.models.SimpleTask;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //testCreateThreads();

        //testMultiThreads();

        testInteruptedThread();
    }

    private static void testInteruptedThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    System.out.println("Thread to interrupt n°" + i);
                    Thread.sleep(600);
                }catch (InterruptedException e ){
                    System.out.println("Thread interrompu !");
                    break;
                }
            }
        });

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
        System.out.println("Done !!");
    }

    private static void testMultiThreads() {
        for (int i = 0; i < 5; i++) {
            new Thread(new SimpleTask("Thread-"+i)).start();
        }
        System.out.println("Tous les thread sont démarrés !");
    }

    private static void testCreateThreads() throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        thread.start();

        new Thread(new MyRunnable()).start();

        new Thread(() -> {
            for(int i = 0; i< 5; i++){
                System.out.println("Thread lambda : " + Thread.currentThread().getName() + " " + i);
            }
        }).start();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            for(int i = 0; i<5; i++){
                System.out.println("Thread Callable : " + Thread.currentThread().getName() + " " + i);
            }
            return "Callable done ! ";
        };
        Future<String> result = executorService.submit(callable);
        System.out.println(result.get());
        executorService.shutdown();

    }
}