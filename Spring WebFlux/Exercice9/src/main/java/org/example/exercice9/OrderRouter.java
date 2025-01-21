package org.example.exercice9;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class OrderRouter {

    @Bean
    public RouterFunction<ServerResponse> orderRoutes(OrderHandler orderHandler) {
        return route(GET("/api/orders"), orderHandler::getAllOrder)
                .andRoute(GET("/api/orders/{id}"), orderHandler::getOrderById)
                .andRoute(GET("/api/orders/search"), orderHandler::getOrderByStatus)
                .andRoute(GET("/api/orders/paged"), orderHandler::getAllOrderPaged)
                .andRoute(POST("/api/orders"), orderHandler::addOrder)
                .andRoute(PUT("/api/orders/{id}"), orderHandler::updateOrder)
                .andRoute(DELETE("/api/orders/{id}"), orderHandler::deleteOrderById);
    }
}
