package models;

import interfaces.Animal;

public class Dog implements Animal {

    @Override
    public void makeSound() {
        System.out.println("Dog : Woaf woaf !");
    }
}
