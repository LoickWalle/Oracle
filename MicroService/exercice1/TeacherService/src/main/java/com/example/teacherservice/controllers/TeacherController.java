package com.example.teacherservice.controllers;

import com.example.teacherservice.dtos.TeacherDTO;
import com.example.teacherservice.services.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public List<TeacherDTO> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/{id}")
    public TeacherDTO getTeacherById(@PathVariable String id) {
        return teacherService.getTeacherById(UUID.fromString(id));
    }

    @PostMapping
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacherDTO) {
        return teacherService.addTeacher(teacherDTO);
    }

    @PutMapping("/{id}")
    public TeacherDTO updateTeacher(@PathVariable String id, @RequestBody TeacherDTO teacher) {
        return teacherService.updateTeacher(UUID.fromString(id), teacher);
    }

    @DeleteMapping("/{id}")
    public boolean deleteTeacher(@PathVariable String id) {
        return teacherService.deleteTeacher(UUID.fromString(id));
    }
}