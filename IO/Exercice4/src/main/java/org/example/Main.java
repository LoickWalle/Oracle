package org.example;

import org.example.models.Book;
import org.example.models.Library;
import org.example.utils.SerializeUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Dragor le dragon", "Cpt.Kirk"));
        books.add(new Book("Echo le dauphin", "Megadrive"));
        books.add(new Book("Chipper le renard", "Dora dora"));

        SerializeUtils.serializeLibrary(new Library(books));
        System.out.println(SerializeUtils.deserializeLibrary());
    }
}