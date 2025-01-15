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

    public ClassResponseDTO getClassById(UUID id) {
        return classRepository.findById(id)
                .map(aClass -> {
                    try {
                        TeacherDTO teacherDTO = getTeacherById(aClass.getTeacherID().toString());
                        List<StudentDTO> studentDTOs = getStudentsByIds(
                                aClass.getStudentsID().stream()
                                        .map(UUID::toString)
                                        .toList()
                        );

                        return buildClassResponseDTO(teacherDTO, studentDTOs);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .orElse(null);
    }

    public ClassResponseDTO createClass(ClassRequestDTO classRequestDTO) throws JsonProcessingException {
        TeacherDTO teacherDTO = getTeacherById(classRequestDTO.getTeacher());
        List<StudentDTO> studentDTOs = getStudentsByIds(classRequestDTO.getStudents());

        if (teacherDTO != null && studentDTOs != null && !studentDTOs.isEmpty()) {
            List<UUID> studentUuids = classRequestDTO.getStudents().stream()
                    .map(UUID::fromString)
                    .toList();
            UUID teacherUuid = UUID.fromString(classRequestDTO.getTeacher());

            classRepository.save(new Class(studentUuids, teacherUuid));

            return buildClassResponseDTO(teacherDTO, studentDTOs);
        }

        return null;
    }

    private TeacherDTO getTeacherById(String id) {
        RestClient<String> restClient = new RestClient<>("http://localhost:8083/api/teacher/" + id);
        String teacherResponse = restClient.getRequest(String.class);
        try {
            return om.readValue(teacherResponse, TeacherDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<StudentDTO> getStudentsByIds(List<String> ids) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>("http://localhost:8082/api/student/pick");
        String studentRequestBody = om.writeValueAsString(ids);
        String studentResponse = restClient.postRequest(studentRequestBody, String.class);
        return om.readValue(studentResponse, new TypeReference<>() {});
    }

    private ClassResponseDTO buildClassResponseDTO(TeacherDTO teacherDTO, List<StudentDTO> studentDTOs) {
        ClassResponseDTO classResponseDTO = new ClassResponseDTO();
        classResponseDTO.setTeacher(teacherDTO);
        classResponseDTO.setStudents(studentDTOs);
        return classResponseDTO;
    }
}
