package org.example.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private static final String LIBRARY_FILE_PATH = "library.ser"; // File path constant
    private static final long serialVersionUID = 1L;
    private List<Book> allBooks = new ArrayList<>();

    public Library() {
    }

    public static Library loadLibrary() {
        File file = new File(LIBRARY_FILE_PATH);

        if (!file.exists()) {
            System.out.println("Pas de bibliothèque sauvegardée !");
            return null;
        }

        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
            return (Library) stream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur de deserialisation : " + e.getMessage());
            return null;
        }
    }

    public void saveLibrary(Library library) {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE_PATH))) {
            stream.writeObject(library);
            System.out.println("La bibliothèque a été serialisée !");
        } catch (IOException e) {
            System.err.println("Erreur de serialisation : " + e.getMessage());
        }
    }

    public void addBook(Book book){
        allBooks.add(book);
        saveLibrary(this);
    }

    @Override
    public String toString() {
        StringBuilder library = new StringBuilder("== Library ==\n");
        for(Book book : allBooks){
            library.append(book).append("\n");
        }
        return library.toString();
    }
}
