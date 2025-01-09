package org.example.models;

import java.io.Serializable;

public class Monster extends Entity implements Serializable {
    public Monster(String name, int strength, int health) {
        super(name, strength, health);
    }
}
