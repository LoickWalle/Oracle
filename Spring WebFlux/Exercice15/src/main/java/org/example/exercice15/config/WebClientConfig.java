package org.example.exercice15.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    /*
        Nombre de requete en cours : http://localhost:8080/actuator/metrics/http.server.requests.active
        La durée moyenne des requêtes HTTP : http://localhost:8080/actuator/metrics/http.server.requests
    */
    @Bean
    public WebClient webClient() {
        ConnectionProvider provider = ConnectionProvider.builder("custom")
                .maxConnections(50)
                .disposeTimeout(Duration.ofSeconds(10))
                .pendingAcquireMaxCount(10)
                .build();

        return WebClient.builder()
                .baseUrl("http://localhost:8080/")
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                .build();
    }
}
