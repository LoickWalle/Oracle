package org.example.exercice1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Dinosaurs {
    @Id
    private long id;
    private String name;
    private String species;
    private double age_million_years;

    public Dinosaurs() {
    }

    public Dinosaurs(long id, String name, String species, double age_million_years) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.age_million_years = age_million_years;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getAge_million_years() {
        return age_million_years;
    }

    public void setAge_million_years(double age_million_years) {
        this.age_million_years = age_million_years;
    }
}
