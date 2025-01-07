package org.example;

import org.example.utils.TextUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class JournalText {

    public static void addEntry(String message, String filepath, List<String> possibleActivities, String filepathBinary){
        AtomicBoolean isCorrectActivity = new AtomicBoolean(false);
        possibleActivities.forEach(s -> {
            if(s.equalsIgnoreCase(message))
                isCorrectActivity.set(true);
        });

        if (!isCorrectActivity.get())
            return;

        List<String> journal = TextUtils.readTextFile(filepath);
        journal.add(message + " - " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        TextUtils.writeTextFile(journal, filepath);

        if(journal.size() % 5 == 0)
            JournalBinary.copyJournalToBinary(filepath, filepathBinary);
    }

    public static void displayJournal(String filepath){
        List<String> journal = TextUtils.readTextFile(filepath);
        journal.forEach(System.out::println);
    }
}
