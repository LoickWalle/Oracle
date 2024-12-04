package Models;

import java.util.UUID;

public class Bird extends Animal {
    public Bird(UUID id, String name, String species) {
        super(id, name, species);
    }

    @Override
    public String getDetails() {
        return getName() + "(" + getSpecies() + ")";
    }

    @Override
    public void eat() {
        System.out.println(getName() + " est en train de manger des graines.");
    }
}
