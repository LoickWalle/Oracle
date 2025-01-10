package org.example.utils;

import org.example.models.Monster;

import java.util.ArrayList;
import java.util.List;

public class MonsterUtils {
    private static final String MONSTERS_FILE_PATH = "monsters.txt";

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
}
