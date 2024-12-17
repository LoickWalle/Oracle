package org.example;

import org.example.models.BankAccount;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();

        Thread[] threads = new Thread[3];

        threads[0] = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock){
                    bankAccount.deposit();
                }
            }
        });

        threads[1] = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock){
                    bankAccount.withdraw();
                }
            }
        });

        threads[2] = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock){
                    bankAccount.deposit();
                }
            }
        });

        runThreads(threads);
        System.out.println("Solde final : " + BankAccount.getSolde());
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }
}