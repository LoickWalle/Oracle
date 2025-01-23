package org.example.exercice15.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private int taskCounter = 0;

    @GetMapping("/stream")
    public Flux<String> stream() {
        return Flux.interval(Duration.ofMillis(500))
                .flatMap(i -> Flux.just("Task "+taskCounter++ +", "));
    }
}
