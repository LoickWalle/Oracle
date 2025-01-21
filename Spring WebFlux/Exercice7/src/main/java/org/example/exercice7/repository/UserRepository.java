package org.example.exercice7.repository;

import org.example.exercice7.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
}
