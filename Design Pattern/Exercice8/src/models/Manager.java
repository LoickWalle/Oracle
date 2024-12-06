package models;

import interfaces.IEmployee;

import java.util.ArrayList;
import java.util.List;

public class Manager implements IEmployee {
    private String name;
    private int age;

    private List<IEmployee> employeeList = new ArrayList<>();

    public Manager(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void add(IEmployee employee){
        employeeList.add(employee);
    }

    public void remove(IEmployee employee){
        employeeList.remove(employee);
    }

    @Override
    public void showDetails() {
        System.out.println("Manager named : "+ name + ", age : " +age);
        for (IEmployee employee : employeeList){
            employee.showDetails();
        }
    }
}
