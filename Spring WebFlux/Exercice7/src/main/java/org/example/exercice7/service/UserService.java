package org.example.exercice7.service;

import org.example.exercice7.entity.User;
import org.example.exercice7.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Flux<User> getAllUser() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Mono<User> addUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(UUID id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setActive(user.isActive());
                    return userRepository.save(existingUser);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Boolean> deleteUserById(UUID id) {
        return userRepository.findById(id)
                .flatMap(existingUser ->
                        userRepository.deleteById(id)
                                .then(Mono.just(true))
                )
                .switchIfEmpty(Mono.just(false));
    }
}
