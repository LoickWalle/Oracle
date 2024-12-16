package org.example.models;

import java.util.concurrent.Callable;

public class CalculateurCallable implements Callable {
    private final int nb;

    public CalculateurCallable(int nb) {
        this.nb = nb;
    }

    @Override
    public Object call() throws Exception {
        return Math.pow(nb, 3);
    }
}
