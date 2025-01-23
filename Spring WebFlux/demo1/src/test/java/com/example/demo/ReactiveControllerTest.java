package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(controllers = ReactiveController.class)
public class ReactiveControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testGetMono() {
        webTestClient.get()
            .uri("/api/test/mono")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("mono");
    }

    @Test
    public void testGetFlux() {
        webTestClient.get()
                .uri("/api/test/flux")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Integer.class).contains(1, 2, 3, 4, 5);
    }

}
