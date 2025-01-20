package org.example.exercice6.controller.bookController;

import org.example.exercice6.entity.Book;
import org.example.exercice6.service.BookService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class BookHandler {

    private final BookService bookService;

    public BookHandler(BookService bookService) {
        this.bookService = bookService;
    }

    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(bookService.getAllBooks(), Book.class);
    }

    public Mono<ServerResponse> getBookByTitle(ServerRequest request) {
        return bookService.getBookByTitle(request.queryParam("title").orElse(""))
                .flatMap(book -> ServerResponse.ok().bodyValue(book))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(bookService::addBook)
                .flatMap(task -> ServerResponse.created(request.uri()).bodyValue(task));
    }

    public Mono<ServerResponse> deleteBookByID(ServerRequest request) {
        UUID bookID = UUID.fromString(request.pathVariable("id"));
        return bookService.deleteBookByID(bookID)
                .flatMap(deleted -> {
                    if (deleted)
                        return ServerResponse.ok().bodyValue(true);
                    return ServerResponse.notFound().build();
                });
    }
}
