package com.example.studentservice.services;

import com.example.studentservice.dtos.StudentDTO;
import com.example.studentservice.entities.Student;
import com.example.studentservice.mapper.StudentMapper;
import com.example.studentservice.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.studentRepository.save(new Student("Test","Tetest", LocalDate.now()));
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            studentDTOS.add(StudentMapper.EntityToStudentDTO(student));
        }
        return studentDTOS;
    }

    public Student getStudentById(UUID id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    public Student addStudent(Student student) {
        return this.studentRepository.save(student);
    }

    public Student updateStudent(UUID id, Student student) {
        return this.studentRepository.findById(id).orElse(null);
    }

    public boolean deleteStudent(UUID id) {
        if(this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
