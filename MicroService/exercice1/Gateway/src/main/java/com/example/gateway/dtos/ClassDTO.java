package com.example.gateway.dtos;

import java.util.List;

public class ClassDTO {
    List<StudentDTO> students;
    TeacherDTO teacher;

    public ClassDTO() {
    }

    public ClassDTO(List<StudentDTO> students, TeacherDTO teacher) {
        this.students = students;
        this.teacher = teacher;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }
}
