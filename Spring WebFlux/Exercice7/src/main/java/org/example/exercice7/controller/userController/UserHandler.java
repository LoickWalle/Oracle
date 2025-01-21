package org.example.exercice7.controller.userController;

import org.example.exercice7.entity.User;
import org.example.exercice7.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok().body(userService.getAllUser(), User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        return userService.getUserById(UUID.fromString(request.pathVariable("id")))
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addUser(ServerRequest request) {
        return request.bodyToMono(User.class)
                .flatMap(userService::addUser)
                .flatMap(task -> ServerResponse.created(request.uri()).bodyValue(task));
    }

    public Mono<ServerResponse> updateUser(ServerRequest request) {
        UUID userID = UUID.fromString(request.pathVariable("id"));
        return request.bodyToMono(User.class)
                .flatMap(user -> userService.updateUser(userID, user))
                .flatMap(user -> ServerResponse.accepted().bodyValue(user));
    }

    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        UUID userID = UUID.fromString(request.pathVariable("id"));
        return userService.deleteUserById(userID)
                .flatMap(deleted -> {
                    if (deleted)
                        return ServerResponse.ok().bodyValue(true);
                    return ServerResponse.notFound().build();
                });
    }
}
