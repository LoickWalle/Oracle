package org.example.threads;

import org.example.models.Printer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task implements Runnable{
    private Lock lock;
    private Printer printer;

    public Task(Printer printer, Lock reentrantLock) {
        this.printer = printer;
        this.lock = reentrantLock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " tente d'utiliser l'imprimante...");
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(Thread.currentThread().getName() + " a acquis le verrou et utilise l'imprimante.");
                    printer.test();
                } finally {
                    System.out.println(Thread.currentThread().getName() + " a terminé d'utiliser l'imprimante et libère le verrou.");
                    lock.unlock();
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " n'a pas pu accéder à l'imprimante (temps d'attente dépassé).");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}