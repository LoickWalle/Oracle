package com.example.demo.basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/public/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/user/hello")
    public String hello2() {
        return "Hello User";
    }

    @GetMapping("/admin/hello")
    public String hello3() {
        return "Hello Admin";
    }
}
