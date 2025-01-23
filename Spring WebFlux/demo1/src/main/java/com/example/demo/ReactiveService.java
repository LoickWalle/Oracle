package com.example.demo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReactiveService {

    public Mono<String> getSingleData() {
        return Mono.just("Hello, Webflux !!!!");
    }

    public Flux<Integer> getDataStream() {
        return Flux.range(1,5);
    }

    public Flux<Integer> getErrorStream() {
        return Flux.concat(Flux.range(1,3),Flux.error(new RuntimeException("Test exception")));
    }
}
