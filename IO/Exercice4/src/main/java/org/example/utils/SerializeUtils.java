package org.example.utils;

import org.example.models.Library;

import java.io.*;

public class SerializeUtils {
    public static void serializeLibrary(Library library){
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("library.ser"))){
            stream.writeObject(library);
            System.out.println("La bibliothèque a été serialisé !");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Library deserializeLibrary(){
        System.out.println("Deserialization de la bibliothèque !");
        Library library = null;
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream("library.ser"))){
            library = (Library) stream.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return library;
    }
}
