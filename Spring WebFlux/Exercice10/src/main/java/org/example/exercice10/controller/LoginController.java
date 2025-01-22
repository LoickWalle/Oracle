package org.example.exercice10.controller;

import org.example.exercice10.service.JwtService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    private final JwtService jwtService;

    public LoginController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/user")
    public String loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        return jwtService.generateToken(username, Map.of("role", "USER"));
    }

    @PostMapping("/admin")
    public String loginAdmin(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        return jwtService.generateToken(username, Map.of("role", "ADMIN"));
    }
}
