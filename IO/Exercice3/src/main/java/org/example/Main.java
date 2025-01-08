package org.example;

import java.io.Console;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demo de la console.");

        Console console = System.console();
        if (console == null) {
            System.out.println("Console non disponible");
            return;
        }
        String username = console.readLine("Entrez votre nom d'utilisateur : ");

        char[] password = console.readPassword("Entrez votre mot de passe : ");
        console.printf("Bienvenue, %s!\n", username);
        Arrays.fill(password, ' ');
    }
}