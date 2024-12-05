package models;

import interfaces.IToy;

public class Ball extends Toy {

    public Ball(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Ball do BOINGG !!");
    }
}
