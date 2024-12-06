package models;

public class Guest {
    private String name;

    public Guest(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Guest's name is "+ name;
    }
}
