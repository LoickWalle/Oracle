package org.example.tp_bonus.service;

import org.example.tp_bonus.model.dto.UserDTO;
import org.example.tp_bonus.repository.LoginRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    private final JwtService jwtService;
    private final LoginRepository loginRepository;

    public LoginService(JwtService jwtService, LoginRepository loginRepository) {
        this.jwtService = jwtService;
        this.loginRepository = loginRepository;
    }

    public Mono<String> login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (username == null || password == null) {
            return Mono.error(new IllegalArgumentException("Username and password must not be null"));
        }

        return loginRepository.findAll()
                .filter(user -> user.getUsername().equals(username))
                .next()
                .flatMap(user -> {
                    if(user.getPassword().equals(password)) {
                        List<String> roles = user.isAdmin() ? List.of("ADMIN","USER") : List.of("USER");
                        return Mono.just(jwtService.generateToken(username, roles));
                    }
                    return Mono.error(new IllegalArgumentException("Password is incorrect"));
                })
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User not found")));
    }

}
