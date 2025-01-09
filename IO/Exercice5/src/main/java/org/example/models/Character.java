package org.example.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Character extends Entity implements Serializable {
    private static final String CHARACTER_FILE_PATH = "characters.dat";

    public Character(String name, int strength, int health) {
        super(name, strength, health);
    }

    public static void saveNewCharacter(Character character) {
        List<Character> characters = loadCharacters();
        characters.add(character);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CHARACTER_FILE_PATH))) {
            for (Character c : characters) {
                out.writeObject(c);
            }
            System.out.println("Personnage créé avec succès et sauvegardé.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du personnage : " + e.getMessage());
        }
    }

    public static void displayCharacters(List<Character> characters) {
        if (!isCharacterFileExisting()) return;

        int counter = 0;
        for (Character character : characters) {
            System.out.print(++counter + ". ");
            System.out.println(character);
        }
    }

    public static Character pickACharacter(int choice) {
        if (!isCharacterFileExisting()) return null;

        List<Character> characters = loadCharacters();
        if (choice > 0 && choice <= characters.size()) {
            return characters.get(choice - 1);
        } else {
            System.out.println("Choix invalide.");
        }

        return null;
    }

    public static List<Character> loadCharacters() {
        List<Character> characters = new ArrayList<>();

        if (!isCharacterFileExisting()) return characters;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(CHARACTER_FILE_PATH))) {
            while (true) {
                try {
                    Character character = (Character) in.readObject();
                    characters.add(character);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors de la lecture des personnages : " + e.getMessage());
        }

        return characters;
    }


    private static boolean isCharacterFileExisting() {
        File file = new File(CHARACTER_FILE_PATH);
        return file.exists();
    }

    @Override
    public String toString() {
        return getName() + " : force (" + getStrength() + "), santé (" + getHealth() + ')';
    }
}
