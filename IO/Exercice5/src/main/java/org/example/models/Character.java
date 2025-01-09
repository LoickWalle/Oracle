package org.example.models;

import java.io.Serializable;

public class Character extends Entity implements Serializable {
    public Character(String name, int strength, int health) {
        super(name, strength, health);
    }

    @Override
    public String toString() {
        return getName() + " : force (" + getStrength() + "), santé (" + getHealth() + ')';
    }
}
