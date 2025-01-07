package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int javaCount = 0;
        String filePath = "java.txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write("Java World !");
            writer.newLine();
            writer.write("Coucou tout le monde, java bien ?");
        } catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                int index = 0;
                while ((index = line.toLowerCase().indexOf("java", index)) != -1) {
                    javaCount++;
                    index += 4;
                }
            }
            System.out.println("Le mot 'java' est apparu " + javaCount + " fois.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}