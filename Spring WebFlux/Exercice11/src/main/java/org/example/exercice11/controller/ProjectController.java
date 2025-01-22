package org.example.exercice11.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @GetMapping
    public Mono<Map<String, List<String>>> getAllProjects() {
        Map<String, List<String>> response = new HashMap<>();
        response.put("projects", List.of("Projet A", "Projet B", "Projet C", "Projet D"));
        return Mono.just(response);
    }
}
