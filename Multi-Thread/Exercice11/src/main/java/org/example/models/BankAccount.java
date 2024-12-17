package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private static int solde = 0;

    public synchronized void deposit() {
        solde += 10;
        System.out.println(Thread.currentThread().getName() + " a déposé 10, solde actuel : " + solde);
    }

    public synchronized void withdraw() {
        if (solde <= 0)
            System.out.println(Thread.currentThread().getName() + " n'a pas pu retirer 10 (solde insuffisant).");
        else {
            solde -= 10;
            System.out.println(Thread.currentThread().getName() + " a retiré 10, solde actuel :" + solde);
        }
    }

    public static int getSolde() {
        return solde;
    }
}
