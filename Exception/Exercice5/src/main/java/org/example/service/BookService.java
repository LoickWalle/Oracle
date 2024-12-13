package org.example.service;

import org.example.annotation.ExceptionHandled;
import org.example.annotation.Log;
import org.example.annotation.Performance;
import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    List<Book> books = new ArrayList<>();

    @Log
    @Performance
    public Book createBook(Book book) {
        books.add(book);
        return books.contains(book) ? book : null;
    }

    @Log
    @Performance
    @ExceptionHandled
    public Book getBookByName(String name) {
        return books.stream()
                .filter(book -> book.getName().contains(name))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Livre non trouvé !"));
    }

    @Log
    @Performance
    @ExceptionHandled
    public void deleteBook(String name) {
        Book bookToDelete = books.stream()
                .filter(book -> book.getName().contains(name))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Livre non trouvé !"));

        books.remove(bookToDelete);
    }
}
