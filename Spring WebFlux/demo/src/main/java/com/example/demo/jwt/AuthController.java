package com.example.demo.jwt;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        return jwtService.generateToken(username, Map.of("role", "USER"));
    }
}
