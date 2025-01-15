package com.example.studentservice.mapper;

import com.example.commondto.student.StudentDTO;
import com.example.studentservice.entities.Student;

public class StudentMapper {

    public static Student StudentDTOToEntity(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setBirthday(studentDTO.getBirthDate());
        return student;
    }

    public static StudentDTO EntityToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setBirthDate(student.getBirthday());
        return studentDTO;
    }
}
