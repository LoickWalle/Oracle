package Models;

import java.util.UUID;

public class Mammal extends Animal {

    public Mammal(UUID id, String name, String species) {
        super(id, name, species);
    }


    @Override
    public String getDetails() {
        return getName() + "(" + getSpecies() + ")";
    }

    @Override
    public void eat() {
        System.out.println(getName() + " est en train de manger de la viande.");
    }
}
