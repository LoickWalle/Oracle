package org.example.utils;

import org.example.models.Book;
import org.example.models.Library;

import java.io.*;

public class SerializeUtils {

    private static final String LIBRARY_FILE_PATH = "library.ser"; // File path constant

    public static Library deserialiseLibrary() {
        File file = new File(LIBRARY_FILE_PATH);

        if (!file.exists()) {
            System.out.println("Pas de bibliothèque sauvegardée !");
            return null;
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            return (Library) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing the library: " + e.getMessage());
            return null;
        }
    }

    public static void serializeLibrary(Library library) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE_PATH))) {
            stream.writeObject(library);
            System.out.println("La bibliothèque a été serialisée !");
        } catch (IOException e) {
            System.err.println("Error serializing the library: " + e.getMessage());
        }
    }
}
