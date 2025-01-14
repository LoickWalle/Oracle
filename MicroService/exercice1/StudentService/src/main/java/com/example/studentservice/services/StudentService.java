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
        this.studentRepository.save(new Student("Test2","Tetest2", LocalDate.now()));
        this.studentRepository.save(new Student("Test3","Tetest3", LocalDate.now()));
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            studentDTOS.add(StudentMapper.EntityToStudentDTO(student));
        }
        return studentDTOS;
    }

    public List<StudentDTO> getListOfStudentsByIds(List<UUID> studentUuids) {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student student : students) {
            if(studentUuids.contains(student.getId()))
                studentDTOS.add(StudentMapper.EntityToStudentDTO(student));
        }
        return studentDTOS;
    }

    public StudentDTO getStudentById(UUID id) {
        Student student = studentRepository.findById(id).orElse(null);
        return student == null ? null : StudentMapper.EntityToStudentDTO(student);
    }

    public StudentDTO addStudent(StudentDTO studentDTO) {
        Student student = StudentMapper.StudentDTOToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.EntityToStudentDTO(savedStudent);
    }

    public StudentDTO updateStudent(UUID id, StudentDTO studentDTO) {
        if(studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setBirthday(studentDTO.getBirthDate());
            student = this.studentRepository.save(student);
            return StudentMapper.EntityToStudentDTO(student);
        }
        return null;
    }

    public boolean deleteStudent(UUID id) {
        if(this.studentRepository.existsById(id)) {
            this.studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
