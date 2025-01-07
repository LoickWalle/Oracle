package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        int javaCount = 0;
        String filePath = "java.txt";

        initText(filePath);
        readFile(filePath, javaCount);
    }

    private static void readFile(String filePath, int javaCount) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                int index = 0;
                javaCount = getJavaCount(index, line, javaCount);
            }

            extractResult(javaCount);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static int getJavaCount(int index, String line, int javaCount) {
        while ((index = line.toLowerCase().indexOf("java", index)) != -1) {
            if ((index == 0 || (line.charAt(index - 1) == ' ')) && (index + 4 == line.length() || line.charAt(index + 4) == ' '))
                javaCount++;

            index += 4;
        }
        return javaCount;
    }

    private static void extractResult(int javaCount) {
        try(FileWriter fileWriter = new FileWriter("result.txt")){
            fileWriter.write("Le mot 'java' est apparu " + javaCount + " fois.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void initText(String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write("Java World !");
            writer.newLine();
            writer.write("Coucou tout le monde, java bien ?");
            writer.newLine();
            writer.write("J'ai écrit un script en java , c'est donc du javascript ?");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}