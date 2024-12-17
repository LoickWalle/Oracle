package org.example.interfaces;

import org.example.models.BankAccount;

public class DepositThread implements Runnable{

    private final BankAccount bankAccount;

    public DepositThread(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            bankAccount.deposit();
        }
    }
}
