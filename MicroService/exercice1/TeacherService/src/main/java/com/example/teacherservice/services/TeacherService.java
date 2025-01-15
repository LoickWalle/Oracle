package com.example.teacherservice.services;

import com.example.commondto.teacher.TeacherDTO;
import com.example.teacherservice.entities.Teacher;
import com.example.teacherservice.mapper.TeacherMapper;
import com.example.teacherservice.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherRepository.save(new Teacher("ProTest","ProTetest", LocalDate.now()));
    }

    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOS = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherDTOS.add(TeacherMapper.EntityToTeacherDTO(teacher));
        }
        return teacherDTOS;
    }

    public TeacherDTO getTeacherById(UUID id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        return teacher == null ? null : TeacherMapper.EntityToTeacherDTO(teacher);
    }

    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = TeacherMapper.TeacherDTOToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.EntityToTeacherDTO(savedTeacher);
    }

    public TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) {
        if(teacherRepository.findById(id).isPresent()) {
            Teacher teacher = teacherRepository.findById(id).get();
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher.setBirthday(teacherDTO.getBirthDate());
            teacher = this.teacherRepository.save(teacher);
            return TeacherMapper.EntityToTeacherDTO(teacher);
        }
        return null;
    }

    public boolean deleteTeacher(UUID id) {
        if(this.teacherRepository.existsById(id)) {
            this.teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
