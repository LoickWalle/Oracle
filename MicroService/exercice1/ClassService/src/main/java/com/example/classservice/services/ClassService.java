package com.example.classservice.services;

import com.example.classservice.entities.Class;
import com.example.classservice.repositories.ClassRepository;
import com.example.classservice.utils.RestClient;
import com.example.commondto.classes.ClassRequestDTO;
import com.example.commondto.classes.ClassResponseDTO;
import com.example.commondto.student.StudentDTO;
import com.example.commondto.teacher.TeacherDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClassService {
    private final ObjectMapper om;

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository, ObjectMapper om) {
        this.classRepository = classRepository;
        this.om = om;
    }

    public List<ClassResponseDTO> getAllClasses() throws JsonProcessingException {
        List<Class> classes = classRepository.findAll();
        List<ClassResponseDTO> classDTOS = new ArrayList<>();
        for (Class classEntity : classes) {
            ClassResponseDTO classDTO = new ClassResponseDTO();
            classDTO.setStudents(getStudentsByIds(classEntity.getStudentsID().stream().map(UUID::toString).toList()));
            classDTO.setTeacher(getTeacherById(classEntity.getTeacherID().toString()));
            classDTOS.add(classDTO);
        }
        return classDTOS;
    }

    public ClassResponseDTO getClassById(UUID id) throws JsonProcessingException {
        Class classEntity = classRepository.findById(id).orElse(null);
        if(classEntity == null)
            return null;

        ClassResponseDTO classDTO = new ClassResponseDTO();
        classDTO.setTeacher(getTeacherById(classEntity.getTeacherID().toString()));
        classDTO.setStudents(getStudentsByIds(classEntity.getStudentsID().stream().map(UUID::toString).toList()));
        return classDTO;
    }

    public ClassResponseDTO createClass(ClassRequestDTO classRequestDTO) throws JsonProcessingException {
        ClassResponseDTO classResponseDTO = new ClassResponseDTO();
        TeacherDTO teacherDTO = getTeacherById(classRequestDTO.getTeacher());
        List<StudentDTO> studentDTOS = getStudentsByIds(classRequestDTO.getStudents());

        if(teacherDTO !=null && studentDTOS != null && !studentDTOS.isEmpty()) {
            List<UUID> studentUuids = classRequestDTO.getStudents().stream().map(UUID::fromString).toList();
            UUID teacherUuid = UUID.fromString(classRequestDTO.getTeacher());

            this.classRepository.save(new Class(studentUuids, teacherUuid));

            classResponseDTO.setTeacher(teacherDTO);
            classResponseDTO.setStudents(studentDTOS);
            return classResponseDTO;
        }

        return null;
    }

    public TeacherDTO getTeacherById(String id) {
        RestClient<String> restClient = new RestClient<>("http://localhost:8083/api/teacher/"+id);
        String teacherResponse = restClient.getRequest(String.class);
        TeacherDTO teacherDTO = null;
        try {
            teacherDTO = om.readValue(teacherResponse, TeacherDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return teacherDTO;
    }

    public List<StudentDTO> getStudentsByIds(List<String> ids) throws JsonProcessingException {
        RestClient<String> restClient2 = new RestClient<>("http://localhost:8082/api/student/pick");
        String studentRequestBody = om.writeValueAsString(ids);
        String studentResponse = restClient2.postRequest(studentRequestBody, String.class);
        return om.readValue(studentResponse, new TypeReference<>(){});
    }
}
