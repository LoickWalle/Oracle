package org.example.models;

public class SimpleTask implements Runnable{
    private final String threadName;

    public SimpleTask(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println(threadName + " étape " + i);
        }
        System.out.println(threadName + " terminé !");
    }
}
