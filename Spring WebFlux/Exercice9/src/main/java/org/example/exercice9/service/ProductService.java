package org.example.exercice9.service;

import org.example.exercice9.entity.Product;
import org.example.exercice9.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
        return productRepository.findById(id);
    }

    public Mono<Product> getProductByName(String name) {
        return getAllProduct().filter(product -> product.getName().equals(name)).next();
    }

    public Mono<Product> addProduct(Product product) {
        return productRepository.save(product);
    }

    public Mono<Product> updateProduct(UUID id, Product product) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setStock(product.getStock());
                    return productRepository.save(existingProduct);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Product> buyProduct(UUID id, int quantity) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    if(product.getStock() < quantity)
                        return Mono.error(new RuntimeException("Stock is too small!"));

                    product.setStock(product.getStock() - quantity);
                    return productRepository.save(product);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Boolean> deleteProductById(UUID id) {
        return productRepository.findById(id)
                .flatMap(existingProduct ->
                        productRepository.deleteById(id)
                                .then(Mono.just(true))
                )
                .switchIfEmpty(Mono.just(false));
    }
}
