package models;

import interfaces.IToy;

public class NerfGun extends Toy{
    public NerfGun(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Nerf do BANG !!");
    }
}
