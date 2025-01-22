package org.example.exercice10.config;

import org.example.exercice10.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import reactor.core.publisher.Mono;

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
                .pathMatchers(HttpMethod.GET, "/api/room").hasAnyRole("USER", "ADMIN")
                .pathMatchers(HttpMethod.POST, "/api/room").hasRole("ADMIN")
                .pathMatchers(HttpMethod.DELETE, "/api/room").hasRole("ADMIN")
                .anyExchange().authenticated()
                .and()
                .addFilterAt(jwtWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf().disable()
                .build();
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
