package org.example.tp_bonus.controller.productController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(ProductHandler productHandler) {
        return route(GET("/api/products"), productHandler::getAllProduct)
                .andRoute(GET("/api/products/search"), productHandler::getProductByName)
                .andRoute(GET("/api/products/{id}"), productHandler::getProductById)
                .andRoute(POST("/api/products"), productHandler::addProduct)
                .andRoute(PUT("/api/products/{id}"), productHandler::updateProduct)
                .andRoute(PUT("/api/products/{id}/buy"), productHandler::buyProduct)
                .andRoute(DELETE("/api/products/{id}"), productHandler::deleteProductById);
    }
}
