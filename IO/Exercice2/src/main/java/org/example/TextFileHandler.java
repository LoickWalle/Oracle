package org.example;

import java.io.*;

public class TextFileHandler {

    public static void demoTextFileOperation(){
        System.out.println("Demo text file.");
        String filePath = "demo.txt";

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            writer.write("Hello World !");
            writer.newLine();
            writer.write("Coucou tout le monde !");
        } catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
