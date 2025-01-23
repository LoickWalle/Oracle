package org.example.tp_bonus.controller;

import org.example.tp_bonus.model.dto.UserDTO;
import org.example.tp_bonus.service.JwtService;
import org.example.tp_bonus.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody UserDTO user) {
        return loginService.login(user)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
