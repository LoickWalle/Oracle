package org.example.utils;

import org.example.models.Character;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterUtils {
    private static final String CHARACTER_FILE_PATH = "characters.dat";

    public static void saveNewCharacter(org.example.models.Character character) {
        List<org.example.models.Character> characters = loadCharacters();
        characters.add(character);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(CHARACTER_FILE_PATH))) {
            for (org.example.models.Character c : characters) {
                out.writeObject(c);
            }
            System.out.println("Personnage créé avec succès et sauvegardé.");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du personnage : " + e.getMessage());
        }
    }

    public static void displayCharacters(List<org.example.models.Character> characters) {
        if (!isCharacterFileExisting()) return;

        int counter = 0;
        for (org.example.models.Character character : characters) {
            System.out.print(++counter + ". ");
            System.out.println(character);
        }
    }

    public static org.example.models.Character pickACharacter(int choice) {
        if (!isCharacterFileExisting()) return null;

        List<org.example.models.Character> characters = loadCharacters();
        if (choice > 0 && choice <= characters.size()) {
            return characters.get(choice - 1);
        } else {
            System.out.println("Choix invalide.");
        }

        return null;
    }

    public static List<org.example.models.Character> loadCharacters() {
        List<org.example.models.Character> characters = new ArrayList<>();

        if (!isCharacterFileExisting()) return characters;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(CHARACTER_FILE_PATH))) {
            while (true) {
                try {
                    org.example.models.Character character = (Character) in.readObject();
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
}
