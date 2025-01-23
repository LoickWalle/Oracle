package org.example.tp_bonus.controller.productController;

import org.example.tp_bonus.model.dto.ProductDTO;
import org.example.tp_bonus.model.entity.Product;
import org.example.tp_bonus.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ProductHandler {

    private final ProductService productService;

    public ProductHandler(ProductService productService) {
        this.productService = productService;
    }

    public Mono<ServerResponse> getAllProduct(ServerRequest request) {
        return ServerResponse.ok().body(productService.getAllProduct(), ProductDTO.class);
    }

    public Mono<ServerResponse> getProductById(ServerRequest request) {
        return productService.getProductById(UUID.fromString(request.pathVariable("id")))
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getProductByName(ServerRequest request) {
        return productService.getProductByName(request.queryParam("name").orElse(""))
                .flatMap(product -> ServerResponse.ok().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> addProduct(ServerRequest request) {
        return request.bodyToMono(ProductDTO.class)
                .flatMap(productService::addProduct)
                .flatMap(task -> ServerResponse.created(request.uri()).bodyValue(task));
    }

    public Mono<ServerResponse> updateProduct(ServerRequest request) {
        UUID productID = UUID.fromString(request.pathVariable("id"));
        return request.bodyToMono(ProductDTO.class)
                .flatMap(product -> productService.updateProduct(productID, product))
                .flatMap(product -> ServerResponse.accepted().bodyValue(product));
    }

    public Mono<ServerResponse> buyProduct(ServerRequest request) {
        UUID productID = UUID.fromString(request.pathVariable("id"));
        int quantity = Integer.parseInt(request.queryParam("quantity").orElse("0"));

        return productService.buyProduct(productID, quantity)
                .flatMap(product -> ServerResponse.accepted().bodyValue(product))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deleteProductById(ServerRequest request) {
        UUID productID = UUID.fromString(request.pathVariable("id"));
        return productService.deleteProductById(productID)
                .flatMap(deleted -> {
                    if (deleted)
                        return ServerResponse.ok().bodyValue(true);
                    return ServerResponse.notFound().build();
                });
    }
}
