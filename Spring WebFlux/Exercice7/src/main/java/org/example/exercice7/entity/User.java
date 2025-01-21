package org.example.exercice7.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("users")
public class User {
    @Id
    private UUID id;
    private String username;
    private String email;
    private boolean active;

    public User() {
    }

    public User(String username, String email, boolean active) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.email = email;
        this.active = active;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", active=" + active +
                '}';
    }
}
