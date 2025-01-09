package org.example.models;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int strength;
    private int health;

    public Entity(String name, int strength, int health) {
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return name +
                " : force (" + strength +
                "), santé (" + health +
                ')';
    }
}
