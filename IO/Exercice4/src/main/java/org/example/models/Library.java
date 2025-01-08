package org.example.models;

import org.example.utils.SerializeUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Book> allBooks = new ArrayList<>();

    public Library() {
    }

    public void addBook(Book book){
        allBooks.add(book);
        SerializeUtils.serializeLibrary(this);
    }

    @Override
    public String toString() {
        String library = "== Library ==\n";
        for(Book book : allBooks){
            library += book + "\n";
        }
        return library;
    }
}
