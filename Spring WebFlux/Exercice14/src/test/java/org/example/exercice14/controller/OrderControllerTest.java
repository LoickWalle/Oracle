package org.example.exercice14.controller;

import org.example.exercice14.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@WebFluxTest(controllers = OrderController.class)
public class OrderControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetAllOrders() {
        Order expectedOrder = new Order(1, "Laptop");
        Order expectedOrder2 = new Order(2, "Phone");

        webTestClient.get()
                .uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class).value(orders -> {
                    assertThat(orders).anyMatch(order ->
                            order.getId() == expectedOrder.getId() && order.getItem().equals(expectedOrder.getItem()));
                    assertThat(orders).anyMatch(order ->
                            order.getId() == expectedOrder2.getId() && order.getItem().equals(expectedOrder2.getItem()));
                });
    }

    @Test
    public void testPostOrder() {
        Order expectedOrder = new Order(3, "Screen");

        webTestClient.post()
                .uri("/api/orders")
                .bodyValue(expectedOrder)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).isEqualTo(expectedOrder);

        webTestClient.get()
                .uri("/api/orders")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Order.class).contains(expectedOrder);
    }
}
