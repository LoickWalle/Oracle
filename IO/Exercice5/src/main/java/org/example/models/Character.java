package org.example.models;

import java.io.*;

public class Character {
    private static final String CHARACTER_FILE_PATH = "character.dat";
    private String name;
    private int strength;
    private int health;

    public Character(String name, int strength, int health) {
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public static void saveNewCharacter(Character character) {
        String line = "";
        line += character.getName() + ", ";
        line += character.getStrength() + ", ";
        line += character.getHealth();

        try (BufferedOutputStream out =
                     new BufferedOutputStream(new FileOutputStream(CHARACTER_FILE_PATH, true))) {
            out.write(line.getBytes());
            out.write(System.lineSeparator().getBytes());
            System.out.println("Personnage créé avec succès et sauvegardé.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayCharacters(){
        if (isCharacterFileExisting()) return;

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(CHARACTER_FILE_PATH))){
            StringBuilder stringBuilder = loadCharacters(in);

            String[] lines = stringBuilder.toString().split(System.lineSeparator());
            for (String line : lines) {
                displayACharacter(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static StringBuilder loadCharacters(BufferedInputStream in) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        StringBuilder stringBuilder = new StringBuilder();

        while ((bytesRead = in.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, bytesRead));
        }
        return stringBuilder;
    }

    private static boolean isCharacterFileExisting() {
        File file = new File(CHARACTER_FILE_PATH);

        if (!file.exists()) {
            System.out.println("Aucun personnage disponible...");
            return true;
        }
        return false;
    }

    public static void displayACharacter(String line){
        line = line.trim();
        String[] stats = line.split(", ");
        Character character = new Character(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2]));
        System.out.println(character);
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public String toString() {
        return name +
                " : force (" + strength +
                "), santé (" + health +
                ')';
    }
}
