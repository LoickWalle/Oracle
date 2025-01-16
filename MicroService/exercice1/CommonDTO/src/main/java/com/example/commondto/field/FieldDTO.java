package com.example.commondto.field;

public class FieldDTO {
    private String name;
    private int coefficient;

    public FieldDTO() {
    }

    public FieldDTO(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
