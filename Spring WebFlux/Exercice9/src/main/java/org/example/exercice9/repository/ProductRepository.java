package org.example.exercice9.repository;

import org.example.exercice9.entity.Product;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ProductRepository extends ReactiveCrudRepository<Product, UUID> {
}
