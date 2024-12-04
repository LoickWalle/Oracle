package Models;

import java.util.UUID;

public abstract class Animal {
    private UUID id;
    private String name;
    private String species;

    public Animal(UUID id, String name, String species) {
        this.id = id;
        this.name = name;
        this.species = species;
    }

    public abstract String getDetails();
    public abstract void eat();

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
}
