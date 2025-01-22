package org.example.exercice10.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @GetMapping
    public Mono<String> getAllRooms() {
        return Mono.just("Test");
    }

    @PostMapping
    public Mono<String> addRoom() {
        return Mono.just("Test add");
    }

    @DeleteMapping
    public Mono<String> deleteRoom() {
        return Mono.just("Test delete");
    }
}
