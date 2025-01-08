package org.example;

import org.example.models.Character;
import org.example.models.Monster;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            displayMenu();
            choice = getUserChoice();
            chooseCharacter(choice);
        } while (choice != 3);

        System.out.println("--- Début de l'aventure ---");

        System.out.println(Monster.pickRandomMonster());
    }

    private static void chooseCharacter(int choice) {
        switch (choice) {
            case 1:
                Character.saveNewCharacter(new Character(chooseCharacterName(), chooseCharacterStrength(), chooseCharacterHealth()));
                break;
            case 2:
                Character.displayCharacters();
                break;
            case 3:
                System.out.println("Au revoir !!");
                break;
            default:
                System.out.println("Choix invalide ! Veuillez entrer un choix valide.");
        }
    }

    private static void displayMenu() {
        System.out.println("""
                
                --- Bienvenue dans le jeu d'aventure textuelle ! ---
                1. Créer un nouveau personnage
                2. Charger un personnage existant
                3. Quitter
                """);
        System.out.print("Votre choix : ");
    }

    public static int getUserChoice(){
        int choice = 0;
        try{
            choice = sc.nextInt();
        } catch (InputMismatchException e){
            System.err.println("Le choix doit être un nombre !");
        }
        return choice;
    }

    public static String chooseCharacterName(){
        sc.nextLine();
        System.out.print("Entrez le nom de votre héros : ");
        return sc.nextLine();
    }

    public static int chooseCharacterHealth(){
        System.out.print("Entrez la santé (1-100) : ");
        return sc.nextInt();
    }

    public static int chooseCharacterStrength(){
        System.out.print("Entrez la force (1-100) : ");
        return sc.nextInt();
    }

}
