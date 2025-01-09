package org.example;

import org.example.models.Character;
import org.example.models.Monster;
import org.example.utils.CharacterUtils;
import org.example.utils.MonsterUtils;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isDead = false;
        Character character = chooseCharacter();
        if(character == null) return;

        System.out.println("--- Début de l'aventure ---");

        for(int i = 0; i < 10; i++){
            fight(character, MonsterUtils.pickRandomMonster());
            if(character.getHealth()<=0){
                isDead = true;
                break;
            }
        }

        if(isDead)
            System.out.println("GAME OVER");
        else
            System.out.println("Félication ! Vous avez terminé l'aventure !");
    }

    private static void fight(Character character, Monster monster){
        System.out.println(character.getName() + " engage le combat face à " + monster.getName());
        if(character.getStrength() >= monster.getStrength())
            System.out.println(character.getName() + " a mis une rouste à " + monster.getName());
        else{
            System.out.println(character.getName() + " c'est fait botter les fesses par " + monster.getName());
            character.setHealth(character.getHealth() - 10);
        }
        System.out.println(character.getName() + " a " + character.getHealth() + " points de vie.");
    }

    private static Character chooseCharacter() {
        int choice;
        Character character = null;

        do {
            displayCharacterMenu();
            choice = getUserChoice();
            switch (choice) {
                case 1:
                    character = createNewCharacter();
                    break;
                case 2:
                    character = loadExistingCharacter();
                    break;
                case 3:
                    System.out.println("Au revoir !!");
                    break;
                default:
                    System.out.println("Choix invalide ! Veuillez entrer un choix valide.");
            }
        } while (character == null && choice != 3);

        return character;
    }

    private static Character createNewCharacter() {
        String name = chooseCharacterName();
        int strength = chooseCharacterStrength();
        int health = chooseCharacterHealth();

        Character character = new Character(name, strength, health);
        CharacterUtils.saveNewCharacter(character);
        return character;
    }

    private static Character loadExistingCharacter() {
        List<Character> characters = CharacterUtils.loadCharacters();

        if (characters.isEmpty()) {
            System.out.println("Aucun personnage disponible...");
            return null;
        }

        do {
            CharacterUtils.displayCharacters(characters);
            System.out.print("Votre choix : ");
            Character character = CharacterUtils.pickACharacter(getUserChoice());
            if (character != null) return character;
        } while (true);
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
            sc.nextLine();
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
