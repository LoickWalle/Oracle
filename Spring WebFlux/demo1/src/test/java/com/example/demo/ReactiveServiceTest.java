package com.example.demo;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class ReactiveServiceTest {

    private final ReactiveService reactiveService = new ReactiveService();

    @Test
    public void testGetSingleData() {
        StepVerifier.create(reactiveService.getSingleData())
                .expectSubscription()
                .expectNext("Hello, Webflux !!!!")
                .verifyComplete();
    }

    @Test
    public void testGetDataStream() {
        StepVerifier.create(reactiveService.getDataStream())
                .expectSubscription()
                .expectNext(1,2,3,4,5)
                .verifyComplete();
    }

    @Test
    public void testErrorStream() {
        StepVerifier.create(reactiveService.getErrorStream())
                .expectSubscription()
                .expectNext(1,2,3)
                .expectErrorMatches(throwable -> throwable instanceof RuntimeException
                        && throwable.getMessage().equalsIgnoreCase("Test exception"))
                .verify();
    }
}
