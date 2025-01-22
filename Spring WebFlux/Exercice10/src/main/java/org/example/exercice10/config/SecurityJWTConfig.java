package org.example.exercice10.config;

import org.example.exercice10.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebFluxSecurity
public class SecurityJWTConfig {

    private final JwtService jwtService;

    public SecurityJWTConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        AuthenticationWebFilter jwtWebFilter = new AuthenticationWebFilter(authenticationManager());
        jwtWebFilter.setServerAuthenticationConverter(new JwtAuthentificationFilter(jwtService));

        return http
                .authorizeExchange()
                    .pathMatchers("/api/login/**").permitAll()
                    .pathMatchers(HttpMethod.GET, "/api/rooms").hasAnyRole("USER", "ADMIN")
                    .pathMatchers(HttpMethod.POST, "/api/rooms").hasRole("ADMIN")
                    .pathMatchers(HttpMethod.DELETE, "/api/rooms").hasRole("ADMIN")
                    .anyExchange().authenticated()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler())
                    .accessDeniedHandler(forbiddenHandler())
                .and()
                .addFilterAt(jwtWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();
    }

    @Bean
    public ServerAuthenticationEntryPoint unauthorizedHandler() {
        return (exchange, exception) -> {
            String responseBody = """                    
                    {                        
                    "error": "UNAUTHORIZED",                        
                    "message": "Vous devez vous connecter pour accéder à cette ressource"                    
                    }                    
                    """;
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            exchange.getResponse().getHeaders().add("Content-Type", "application/json");
            byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
        };
    }

    @Bean
    public ServerAccessDeniedHandler forbiddenHandler() {
        return (exchange, exception) -> {
            String responseBody = """                    
                    {                        
                    "error": "FORBIDDEN",                        
                    "message": "Vous n'avez pas les droits nécessaires pour accéder à cette ressource"                    
                    }                    
                    """;
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            exchange.getResponse().getHeaders().add("Content-Type", "application/json");
            byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
        };
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        return authentication -> {
            if(authentication instanceof UsernamePasswordAuthenticationToken)
                return Mono.just(authentication);
            return Mono.empty();
        };
    }
}
