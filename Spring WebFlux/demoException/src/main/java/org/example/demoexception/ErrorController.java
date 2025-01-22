package org.example.demoexception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/errors")
public class ErrorController {

    @GetMapping("/{id}")
    public Mono<String> getError(@PathVariable String id) {
        return Mono.just(id)
                .map(v -> {
                    if("error".equalsIgnoreCase(v)) {
                        throw new IllegalArgumentException("ID not valid !");
                    }
                    return "ID valide: " + v;
                })
                .onErrorResume(e -> Mono.just(e.getMessage()));
    }

    @GetMapping("/global")
    public Mono<String> getGlobalError() {
        return Mono.error(new RuntimeException("Global error !!!"));
    }
}
