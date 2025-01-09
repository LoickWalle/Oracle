package org.example;

import org.example.models.Character;
import org.example.models.Monster;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Character character;

        character = chooseCharacter();

        if(character == null)
            return;

        System.out.println("--- Début de l'aventure ---");

        System.out.println(Monster.pickRandomMonster());
    }


    private static Character chooseCharacter() {
        int choice;
        Character character = null;

        do {
            displayCharacterMenu();
            choice = getUserChoice();
            switch (choice) {
                case 1:
                    character = new Character(chooseCharacterName(), chooseCharacterStrength(), chooseCharacterHealth());
                    Character.saveNewCharacter(character);
                    break;
                case 2:
                    List<Character> characters = Character.loadCharacters();
                    if(characters.isEmpty()){
                        System.out.println("Aucun personnage disponible...");
                        break;
                    }
                    do {
                        Character.displayCharacters(characters);
                        System.out.print("Votre choix : ");
                        character = Character.pickACharacter(getUserChoice());
                    }while (character == null);
                    break;
                case 3:
                    System.out.println("Au revoir !!");
                    break;
                default:
                    System.out.println("Choix invalide ! Veuillez entrer un choix valide.");
            }
        }while (character == null && choice != 3);

        return character;
    }

    private static void displayCharacterMenu() {
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
