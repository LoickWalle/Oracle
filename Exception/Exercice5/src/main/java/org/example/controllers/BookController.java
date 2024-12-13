package org.example.controllers;

import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @GetMapping("/{name}")
    public Book getBookByName(@PathVariable String name) {
        return bookService.getBookByName(name);
    }

    @DeleteMapping("/{name}")
    public String deleteBook(@PathVariable String name) {
        try {
            bookService.deleteBook(name);
            return "Livre avec le nom '" + name + "' supprimé avec succès.";
        }catch (BookNotFoundException e){
            System.out.println(e.getMessage());;
            return null;
        }
    }
}
