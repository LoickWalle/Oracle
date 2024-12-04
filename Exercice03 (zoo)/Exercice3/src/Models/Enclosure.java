package Models;

import java.util.ArrayList;
import java.util.List;

public class Enclosure {
    private int id;
    private String name;
    private List<Animal> animalList = new ArrayList<>();

    public Enclosure(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public void addAnimal(Animal animalToAdd){
        animalList.add(animalToAdd);
    }

    public void displayAllAnimals(){
        System.out.println("Enclos " + name + " : ");

        if(animalList.isEmpty()) {
            System.out.println("This enclosure is empty !");
            return;
        }

        System.out.print("[");
        for(Animal animal : animalList){
            System.out.print(animal.getDetails());
        }
        System.out.println("]");
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
