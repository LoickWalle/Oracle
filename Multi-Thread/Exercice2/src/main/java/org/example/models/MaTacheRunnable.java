package org.example.models;

public class MaTacheRunnable implements Runnable{
    private final int nb;

    public MaTacheRunnable(int nb) {
        this.nb = nb;
    }

    @Override
    public void run() {
        System.out.println("Le carré de " + nb + " est " + nb * nb);
    }
}
