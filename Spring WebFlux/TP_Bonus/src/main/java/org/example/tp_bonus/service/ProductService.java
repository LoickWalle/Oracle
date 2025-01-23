package org.example.tp_bonus.service;

import org.example.tp_bonus.model.dto.ProductDTO;
import org.example.tp_bonus.model.entity.Product;
import org.example.tp_bonus.model.mapper.ProductMapper;
import org.example.tp_bonus.repository.ProductRepository;
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

    public Flux<ProductDTO> getAllProduct() {
        return productRepository.findAll()
                .map(ProductMapper::ProductEntityToDTO);
    }

    public Mono<ProductDTO> getProductById(UUID id) {
        return productRepository.findById(id).map(ProductMapper::ProductEntityToDTO);
    }

    public Mono<ProductDTO> getProductByName(String name) {
        return getAllProduct().filter(product -> product.getName().equals(name)).next();
    }

    public Mono<ProductDTO> addProduct(ProductDTO productDTO) {
        Product productToSave = ProductMapper.ProductDTOToProduct(productDTO);
        return productRepository.save(productToSave).map(ProductMapper::ProductEntityToDTO);
    }

    public Mono<ProductDTO> updateProduct(UUID id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .flatMap(existingProduct -> {
                    existingProduct.setName(productDTO.getName());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setStock(productDTO.getStock());
                    return productRepository.save(existingProduct);
                })
                .map(ProductMapper::ProductEntityToDTO)
                .switchIfEmpty(Mono.empty());
    }

    public Mono<ProductDTO> buyProduct(UUID id, int quantity) {
        return productRepository.findById(id)
                .flatMap(product -> {
                    if(product.getStock() < quantity)
                        return Mono.error(new RuntimeException("Stock is too small!"));

                    product.setStock(product.getStock() - quantity);
                    return productRepository.save(product).map(ProductMapper::ProductEntityToDTO);
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
