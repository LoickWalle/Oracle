package org.example;

import org.example.interfaces.DepositThread;
import org.example.interfaces.WithdrawThread;
import org.example.models.BankAccount;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();

        Thread[] threads = new Thread[3];

        threads[0] = new Thread(new DepositThread(bankAccount));
        threads[1] = new Thread(new WithdrawThread(bankAccount));
        threads[2] = new Thread(new DepositThread(bankAccount));

        runThreads(threads);
        System.out.println("Solde final : " + BankAccount.getSolde());
    }

    private static void runThreads(Thread[] threads) throws InterruptedException {
        for (Thread thread : threads) thread.start();
        for (Thread thread : threads) thread.join();
    }
}