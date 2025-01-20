package org.example.exercice6.service;

import org.example.exercice6.entity.Book;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.UUID;

@Service
public class BookService {
    HashMap<UUID, Book> books = new HashMap<>();

    public BookService() {
        UUID uuid = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();
        books.put(uuid, new Book(uuid, "The witcher", "Andrej"));
        books.put(uuid2, new Book(uuid, "The witcher 2", "Andrej"));
        books.put(uuid3, new Book(uuid, "The witcher 3", "Andrej"));
    }

    public Flux<Book> getAllBooks() {
        return Flux.fromIterable(books.values());
    }

    public Mono<Book> getBookByTitle(String title) {
        return Flux.fromIterable(books.values())
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .next();
    }

    public Mono<Book> addBook(Book book) {
        book.setId(UUID.randomUUID());
        books.put(book.getId(), book);
        return Mono.just(book);
    }

    public Mono<Boolean> deleteBookByID(UUID id) {
        if(books.containsKey(id)){
            books.remove(id);
            return Mono.just(true);
        }
        return Mono.just(false);    }
}
