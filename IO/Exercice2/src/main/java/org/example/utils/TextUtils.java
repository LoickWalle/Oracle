package org.example.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextUtils {
    public static void writeTextFile(List<String> allLines, String filepath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            allLines.forEach(s -> {
                try {
                    writer.write(s);
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<String> readTextFile(String filepath){
        List<String> journal = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while ((line = reader.readLine()) != null){
                journal.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return journal;
    }
}
