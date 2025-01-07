package org.example;

import java.io.*;

public class JournalBinary {

    public static void copyJournalToBinary(String filepath, String binaryFilePath) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(filepath));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(binaryFilePath))
        ) {
            String line;
            while ((line = reader.readLine()) != null){
                out.write(line.getBytes());
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void displayBinaryJournal(String binaryFilePath){
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(binaryFilePath))){
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1){
                System.out.println(new String(buffer, 0, bytesRead));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
