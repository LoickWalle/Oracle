package models;

import interfaces.IPrestation;

import java.util.ArrayList;
import java.util.List;

public class SimplePrestation implements IPrestation {
    private String name;
    List<Guest> guestList = new ArrayList<>();

    public SimplePrestation(String name) {
        this.name = name;
    }

    @Override
    public void showDetails(int spaceNb) {

        for(int i = 0; i < spaceNb; i++){
            System.out.print("\t");
        }
        System.out.println("Simple prestation named "+ name + " with these guests : ");
        for (Guest guest: guestList){
            System.out.println("- " + guest);
        }
    }
}
