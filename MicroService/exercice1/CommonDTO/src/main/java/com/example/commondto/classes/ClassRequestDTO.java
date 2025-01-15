package com.example.commondto.classes;

import java.util.List;

public class ClassRequestDTO {
    List<String> students;
    String teacher;

    public ClassRequestDTO() {
    }

    public ClassRequestDTO(List<String> students, String teacher) {
        this.students = students;
        this.teacher = teacher;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
