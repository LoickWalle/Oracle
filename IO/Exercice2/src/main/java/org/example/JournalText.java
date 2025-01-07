package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JournalText {

    public static void addEntry(String message, String filepath){
        List<String> journal = JournalText.readJournal(filepath);
        journal.add(message + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        JournalText.writeJournal(journal, filepath);
    }

    public static void displayJournal(String filepath){
        List<String> journal = readJournal(filepath);
        journal.forEach(System.out::println);
    }

    public static void writeJournal(List<String> newJournal, String filepath){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))){
            newJournal.forEach(s -> {
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

    public static List<String> readJournal(String filepath){
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
