package com.example.teacherservice.services;

import com.example.commondto.teacher.TeacherDTO;
import com.example.teacherservice.entities.Teacher;
import com.example.teacherservice.mapper.TeacherMapper;
import com.example.teacherservice.repositories.TeacherRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        return teacherRepository.findAll().stream()
                .map(TeacherMapper::EntityToTeacherDTO)
                .toList();
    }

    public TeacherDTO getTeacherById(UUID id) {
        return teacherRepository.findById(id)
                .map(TeacherMapper::EntityToTeacherDTO)
                .orElse(null);
    }

    public TeacherDTO addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = TeacherMapper.TeacherDTOToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.EntityToTeacherDTO(savedTeacher);
    }

    public TeacherDTO updateTeacher(UUID id, TeacherDTO teacherDTO) {
        return teacherRepository.findById(id)
                .map(existingTeacher -> {
                    existingTeacher.setFirstName(teacherDTO.getFirstName());
                    existingTeacher.setLastName(teacherDTO.getLastName());
                    existingTeacher.setBirthday(teacherDTO.getBirthDate());
                    Teacher updatedTeacher = teacherRepository.save(existingTeacher);
                    return TeacherMapper.EntityToTeacherDTO(updatedTeacher);
                })
                .orElse(null);
    }

    public boolean deleteTeacher(UUID id) {
        if(this.teacherRepository.existsById(id)) {
            this.teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
