package org.example.exercice6.controller.bookController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(BookHandler bookHandler) {
        return route(GET("/api/books"), bookHandler::getAllBooks)
                .andRoute(GET("/api/books/search"), bookHandler::getBookByTitle)
                .andRoute(POST("/api/books"), bookHandler::createBook)
                .andRoute(DELETE("/api/books/{id}"), bookHandler::deleteBookByID);
    }
}
