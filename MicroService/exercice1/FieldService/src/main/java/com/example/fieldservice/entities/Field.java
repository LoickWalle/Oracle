package com.example.fieldservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int coefficient;

    public Field() {
    }

    public Field(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
