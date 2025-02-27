package com.example.commondto.classes;

import com.example.commondto.student.StudentDTO;
import com.example.commondto.teacher.TeacherDTO;

import java.util.List;

public class ClassResponseDTO {
    List<StudentDTO> students;
    TeacherDTO teacher;

    public ClassResponseDTO() {
    }

    public ClassResponseDTO(List<StudentDTO> students, TeacherDTO teacher) {
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
