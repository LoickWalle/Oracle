package com.example.studentservice.controllers;

import com.example.commondto.student.StudentDTO;
import com.example.studentservice.services.StudentService;
import org.example.commonconfig.endpoinds.Path;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Path.STUDENT_ENDPOINT)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/pick")
    public List<StudentDTO> getListOfStudentsByIds(@RequestBody List<String> studentIds) {
        List<UUID> uuids = studentIds.stream().map(UUID::fromString).toList();
        return studentService.getListOfStudentsByIds(uuids);
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
