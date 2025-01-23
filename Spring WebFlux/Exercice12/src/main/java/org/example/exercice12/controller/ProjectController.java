package org.example.exercice12.controller;

import org.example.exercice12.entity.Product;
import org.example.exercice12.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
public class ProjectController {
    private final ProductService productService;

    public ProjectController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<Product> getAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable("id") String id) {
        return productService.getProductById(UUID.fromString(id));
    }

    @GetMapping("price/{maxPrice}")
    public Flux<Product> getAllProductByMaxPrice(@PathVariable("maxPrice") double maxPrice) {
        return productService.getAllProductByMaxPrice(maxPrice);
    }

    @PostMapping
    public Mono<Product> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

}
