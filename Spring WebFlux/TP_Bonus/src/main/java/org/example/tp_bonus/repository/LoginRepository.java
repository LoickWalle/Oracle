package org.example.tp_bonus.repository;

import org.example.tp_bonus.model.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface LoginRepository extends ReactiveCrudRepository<User, UUID> {
}
