package com.example.teacherservice.services;

import com.example.teacherservice.entities.Teacher;
import com.example.teacherservice.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(UUID id) {
        return this.teacherRepository.findById(id).orElse(null);
    }

    public Teacher addTeacher(Teacher teacher) {
        return this.teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(UUID id, Teacher teacher) {
        return this.teacherRepository.findById(id).orElse(null);
    }

    public boolean deleteTeacher(UUID id) {
        if(this.teacherRepository.existsById(id)) {
            this.teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
