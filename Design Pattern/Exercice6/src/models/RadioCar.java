package models;

import interfaces.IToy;

public class RadioCar extends Toy {
    public RadioCar(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("RadioCar do VROOOMM !");
    }
}
