package org.example;

import org.example.models.MonPremierThread;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int i = 0; i < 3; i++) {
            new MonPremierThread().start();
        }
    }
}