package org.example.interfaces;

import org.example.models.BankAccount;

public class WithdrawThread implements Runnable{
    private final BankAccount bankAccount;

    public WithdrawThread(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            bankAccount.withdraw();
        }
    }
}
