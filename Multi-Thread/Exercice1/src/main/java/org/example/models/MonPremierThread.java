package org.example.models;

public class MonPremierThread extends Thread{
    @Override
    public void run() {
        for (int i = 1; i<=10 ; i++){
            System.out.println(getName() + " - Compteur : " + i);
        }
    }
}
