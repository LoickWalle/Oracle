package models;

import interfaces.IEmployee;

public class Regular implements IEmployee {
    private String name;
    private int age;

    public Regular(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void showDetails() {
        System.out.println("Employee named : "+ name + ", age : " +age);
    }
}
