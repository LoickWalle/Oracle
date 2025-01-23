package org.example.exercice12.service;

import org.example.exercice12.entity.Product;
import org.example.exercice12.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Mono<Product> getProductById(UUID id) {
        return productRepository.findById(id)
                .flatMap(product -> Mono.just(product))
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    public Mono<Product> addProduct(Product product) {
        if(product.getName() == null)
            return Mono.error(new RuntimeException("Product name cannot be empty"));

        if(product.getPrice() < 0)
            return Mono.error(new RuntimeException("Product price cannot be negative"));

        return productRepository.save(product);
    }

    public Flux<Product> getAllProductByMaxPrice(double maxPrice) {
        if(maxPrice < 0)
            return Flux.error(new RuntimeException("Product price cannot be negative"));

        return productRepository.findAll().filter(product -> product.getPrice() <= maxPrice);
    }

}
