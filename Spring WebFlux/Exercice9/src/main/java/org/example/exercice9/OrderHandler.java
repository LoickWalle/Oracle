package org.example.exercice9;

import org.example.exercice8.entity.Order;
import org.example.exercice8.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class OrderHandler {

    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public Mono<ServerResponse> getAllOrder(ServerRequest request) {
        return ServerResponse.ok().body(orderService.getAllOrder(), Order.class);
    }

    public Mono<ServerResponse> getOrderById(ServerRequest request) {
        return orderService.getOrderById(UUID.fromString(request.pathVariable("id")))
                .flatMap(order -> ServerResponse.ok().bodyValue(order))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getOrderByStatus(ServerRequest request) {
        return ServerResponse.ok()
                .body(orderService.getOrdersByStatus(request.queryParam("status").orElse("")), Order.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getAllOrderPaged(ServerRequest request) {
        long page = Integer.parseInt(request.queryParam("page").orElse("0"));
        long size = Integer.parseInt(request.queryParam("size").orElse("10"));

        return ServerResponse.ok().body(orderService.getAllOrderPaged(page, size), Order.class);
    }

    public Mono<ServerResponse> addOrder(ServerRequest request) {
        return request.bodyToMono(Order.class)
                .flatMap(orderService::addOrder)
                .flatMap(task -> ServerResponse.created(request.uri()).bodyValue(task));
    }

    public Mono<ServerResponse> updateOrder(ServerRequest request) {
        UUID orderID = UUID.fromString(request.pathVariable("id"));
        return request.bodyToMono(Order.class)
                .flatMap(order -> orderService.updateOrder(orderID, order))
                .flatMap(order -> ServerResponse.accepted().bodyValue(order));
    }

    public Mono<ServerResponse> deleteOrderById(ServerRequest request) {
        UUID orderID = UUID.fromString(request.pathVariable("id"));
        return orderService.deleteOrderById(orderID)
                .flatMap(deleted -> {
                    if (deleted)
                        return ServerResponse.ok().bodyValue(true);
                    return ServerResponse.notFound().build();
                });
    }
}
