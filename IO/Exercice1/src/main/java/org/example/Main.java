package org.example;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        String filePath = "java.txt";
        saveResult(countJavaOccurrences(filePath));
    }

    private static int countJavaOccurrences(String filePath) {
        AtomicInteger javaCount = new AtomicInteger(0);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                countJavaInLine(line, javaCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return javaCount.get();
    }

    private static void countJavaInLine(String line, AtomicInteger javaCount) {
        int index = 0;
        while ((index = line.toLowerCase().indexOf("java", index)) != -1) {
            if (isJavaWord(line, index)) {
                javaCount.incrementAndGet();
            }
            index += 4;
        }
    }

    private static boolean isJavaWord(String line, int index) {
        return (index == 0 || line.charAt(index - 1) == ' ') &&
                (index + 4 == line.length() || line.charAt(index + 4) == ' ');
    }

    private static void saveResult(int javaCount) {
        try (FileWriter fileWriter = new FileWriter("result.txt")) {
            fileWriter.write("Le mot 'java' est apparu " + javaCount + " fois.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
