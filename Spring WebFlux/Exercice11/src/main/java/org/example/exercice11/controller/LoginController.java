package org.example.exercice11.controller;

import org.example.exercice11.service.JwtService;
import org.example.exercice11.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final JwtService jwtService;
    private final LoginService loginService;

    public LoginController(JwtService jwtService, LoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if(loginService.login(username, password))
            return jwtService.generateToken(username, List.of("USER"));
        return "Invalid username or password";
    }
}
