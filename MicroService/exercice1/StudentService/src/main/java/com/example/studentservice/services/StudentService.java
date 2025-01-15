package com.example.studentservice.services;

import com.example.commondto.student.StudentDTO;
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
        this.studentRepository.save(new Student("Test2","Tetest2", LocalDate.now()));
        this.studentRepository.save(new Student("Test3","Tetest3", LocalDate.now()));
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::EntityToStudentDTO)
                .toList();
    }

    public List<StudentDTO> getListOfStudentsByIds(List<UUID> studentUuids) {
        return studentRepository.findAll().stream()
                .filter(student -> studentUuids.contains(student.getId()))
                .map(StudentMapper::EntityToStudentDTO)
                .toList();
    }

    public StudentDTO getStudentById(UUID id) {
        return studentRepository.findById(id)
                .map(StudentMapper::EntityToStudentDTO)
                .orElse(null);
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.StudentDTOToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.EntityToStudentDTO(savedStudent);
    }

    public StudentDTO updateStudent(UUID id, StudentDTO studentDTO) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setFirstName(studentDTO.getFirstName());
                    existingStudent.setLastName(studentDTO.getLastName());
                    existingStudent.setBirthday(studentDTO.getBirthDate());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return StudentMapper.EntityToStudentDTO(updatedStudent);
                })
                .orElse(null);
    }

    public boolean deleteStudent(UUID id) {
        if(this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
