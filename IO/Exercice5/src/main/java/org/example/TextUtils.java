package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextUtils {

    public static List<String> readTextFile(String filepath){
        List<String> textFile = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while ((line = reader.readLine()) != null){
                textFile.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return textFile;
    }
}
