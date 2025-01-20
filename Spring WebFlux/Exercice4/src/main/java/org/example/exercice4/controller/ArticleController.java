package org.example.exercice4.controller;

import org.example.exercice4.entity.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @GetMapping("/articles")
    public Flux<String> getArticles() {
        return Flux.just(new Article(1, "Introduction to Spring WebFlux"),
                new Article(2, "Reactive Programming with Project Reactor"),
                new Article(3, "Building APIs with Spring Boot"))
                .map(Article::getTitle);
    }
}
