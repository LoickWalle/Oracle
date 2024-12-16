package org.example.models;

public class MaTâcheRunnable implements Runnable{
    private final int nb;

    public MaTâcheRunnable(int nb) {
        this.nb = nb;
    }

    @Override
    public void run() {
        System.out.println("Le carré de " + nb + " est " + nb * nb);
    }
}
