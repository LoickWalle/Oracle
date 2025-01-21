package org.example.exercice8.service;

import org.example.exercice8.entity.Order;
import org.example.exercice8.enums.Status;
import org.example.exercice8.repository.OrderRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Flux<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Mono<Order> getOrderById(UUID id) {
        return orderRepository.findById(id);
    }

    public Flux<Order> getOrdersByStatus(String status) {
        return Optional.ofNullable(status)
                .flatMap(s -> {
                    try {
                        return Optional.of(Status.valueOf(s));
                    } catch (IllegalArgumentException e) {
                        return Optional.empty();
                    }
                })
                .map(statusEnum -> getAllOrder().filter(order -> order.getStatus().equals(statusEnum)))
                .orElse(Flux.empty());
    }

    public Flux<Order> getAllOrderPaged(long page, long size) {
        return getAllOrder()
                .skip((page - 1) * size)
                .take(size);
    }

    public Flux<Order> getOrderByCustomerName(String customerName) {
        return getAllOrder().filter(order -> order.getCustomerName().equals(customerName));
    }

    public Mono<Order> addOrder(Order order) {
        Order order1 = new Order(order.getCustomerName(), order.getTotalAmount(), order.getStatus());
        return orderRepository.save(order1);
    }

    public Mono<Order> updateOrder(UUID id, Order order) {
        return orderRepository.findById(id)
                .flatMap(existingOrder -> {
                    order.setId(id);
                    return orderRepository.save(order);
                })
                .switchIfEmpty(Mono.empty());
    }

    public Mono<Boolean> deleteOrderById(UUID id) {
        return orderRepository.findById(id)
                .flatMap(existingOrder ->
                        orderRepository.deleteById(id)
                                .then(Mono.just(true))
                )
                .switchIfEmpty(Mono.just(false));
    }
}
