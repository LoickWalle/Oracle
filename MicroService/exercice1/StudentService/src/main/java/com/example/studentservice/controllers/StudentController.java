package com.example.studentservice.controllers;

import com.example.studentservice.dtos.StudentDTO;
import com.example.studentservice.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable String id) {
        return studentService.getStudentById(UUID.fromString(id));
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
        return studentService.addStudent(studentDTO);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable String id, @RequestBody StudentDTO student) {
        return studentService.updateStudent(UUID.fromString(id), student);
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable String id) {
        return studentService.deleteStudent(UUID.fromString(id));
    }
}
