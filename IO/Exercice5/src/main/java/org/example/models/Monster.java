package org.example.models;

import org.example.TextUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Monster implements Serializable {
    private static final String MONSTERS_FILE_PATH = "monsters.txt";
    private String name;
    private int strength;
    private int health;

    public Monster(String name, int strength, int health) {
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public static Monster pickRandomMonster(){
        List<Monster> allMonsters = new ArrayList<>();
        List<String> allTextMonsters = TextUtils.readTextFile(MONSTERS_FILE_PATH);

        for(String line : allTextMonsters){
            line = line.trim();
            String[] stats = line.split(", ");
            allMonsters.add(new Monster(stats[0], Integer.parseInt(stats[1]), Integer.parseInt(stats[2])));
        }

        return allMonsters.get((int) (Math.random() * allMonsters.size()));
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", strength=" + strength +
                ", health=" + health +
                '}';
    }
}
