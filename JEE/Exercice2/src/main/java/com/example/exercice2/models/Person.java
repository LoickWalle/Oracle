package com.example.exercice2.models;

public class Person {
    private String name;
    private String firstname;
    private int age;

    public Person(String name, String firstname, int age) {
        this.name = name;
        this.firstname = firstname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public int getAge() {
        return age;
    }
}
