package app;

import anotherPackage.Game;

public class Main {
    public static void main(String[] args) {
        Game game = new Game("The witcher 3", 55.0);
        System.out.println(game);
    }
}