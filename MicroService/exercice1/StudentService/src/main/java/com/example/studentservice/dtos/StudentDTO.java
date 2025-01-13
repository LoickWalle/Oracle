package com.example.studentservice.dtos;

import lombok.*;

import java.time.LocalDate;

@Setter
@AllArgsConstructor
public class StudentDTO {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public StudentDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
