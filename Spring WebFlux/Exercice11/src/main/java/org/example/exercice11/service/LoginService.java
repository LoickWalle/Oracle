package org.example.exercice11.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final Map<String, String> users = new HashMap<>();

    public LoginService() {
        users.put("admin", "admin");
        users.put("user", "user");
    }

    public boolean login(String username, String password) {
        if(users.containsKey(username)) {
            return users.get(username).equals(password);
        }
        return false;
    }

}
