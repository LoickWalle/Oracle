package org.example;

import java.io.*;

public class BinaryFileHandler {
    public static void demoBinaryFileOperation(){
        System.out.println("Demo binary file.");
        String filePath = "demo.dat";

        try(FileOutputStream writer = new FileOutputStream(filePath)){
            writer.write(new byte[]{1,2,3,4,5,6,7,8,9});
            System.out.println("Fichier binaire crée !!");
        } catch (IOException e){
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath))){
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("demoSource.dat"));
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1){
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
