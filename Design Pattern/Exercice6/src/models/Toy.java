package models;

import decorators.ToyDecorator;
import interfaces.IToy;

public class Toy implements IToy {
    String name;

    public Toy(String name){
        this.name = name;
    }

    @Override
    public void makeSound() {
        System.out.println("Toy do nothing...");
    }

    @Override
    public String getDescription() {
        return "Toy's name is " + name +".";
    }

    public String getName() {
        return name;
    }
}
