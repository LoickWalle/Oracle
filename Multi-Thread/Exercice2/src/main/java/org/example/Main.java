package org.example;

import org.example.models.MaTacheRunnable;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new Thread(new MaTacheRunnable(i)).start();
        }
    }
}