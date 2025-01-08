package org.example.utils;

import org.example.models.Book;
import org.example.models.Library;

import java.io.*;

public class SerializeUtils {

    public static void addBookToLibrary(Book book) {
        try {
            Library library = deserialiseLibrary();

            if (library == null)
                library = new Library();

            library.addBook(book);

            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("library.ser"))) {
                stream.writeObject(library);
                System.out.println("La bibliothèque a été serialisée !");
            }
        } catch (IOException e) {
            System.err.println("Error serializing the library: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Library deserialiseLibrary() {
        System.out.println("Deserialization de la bibliothèque !");

        File file = new File("library.ser");

        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            return (Library) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error deserializing the library: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
