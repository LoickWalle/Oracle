package models;

import interfaces.ILutin;
import interfaces.IToy;

public class Lutin implements ILutin {
    private String name;

    public Lutin(String name) {
        this.name = name;
    }

    @Override
    public void notification(String message) {
        System.out.println(name + " received a message : " + message);
    }

    @Override
    public void makeToy(IToy toy, LutinCommCenter lutinCommCenter) {
        toy.makeSound();
        lutinCommCenter.notifyOtherLutin(name + " made a toy ! " + toy.getDescription(), this);
    }
}
