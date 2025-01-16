package com.example.gateway.controllers;

import com.example.commondto.teacher.TeacherDTO;
import com.example.gateway.utils.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.commonconfig.endpoinds.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Path.TEACHER_ENDPOINT)
public class TeacherController {
    private final ObjectMapper om;

    public TeacherController(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAllTeacher() throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.TEACHER_API_URL);
        String response = restClient.getRequest(String.class);
        List<TeacherDTO> teachers = om.readValue(response, new TypeReference<>() {});
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> getAllTeacher(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.TEACHER_API_URL+"/"+id);
        String response = restClient.getRequest(String.class);
        TeacherDTO teachers = om.readValue(response, TeacherDTO.class);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TeacherDTO> createTeacher(@RequestBody TeacherDTO teacherDTO) throws JsonProcessingException {
        RestClient<TeacherDTO> restClient = new RestClient<>(Path.TEACHER_API_URL);
        TeacherDTO teacherDTOResponse = restClient.postRequest(om.writeValueAsString(teacherDTO), TeacherDTO.class);
        return new ResponseEntity<>(teacherDTOResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherDTO> modifyTeacher(@PathVariable String id, @RequestBody TeacherDTO teacherDTO) throws JsonProcessingException {
        RestClient<TeacherDTO> restClient = new RestClient<>(Path.TEACHER_API_URL+"/"+id);
        TeacherDTO teacherDTOResponse = restClient.putRequest(om.writeValueAsString(teacherDTO), TeacherDTO.class);
        return new ResponseEntity<>(teacherDTOResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTeacher(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.TEACHER_API_URL+"/"+id);
        Boolean response = Boolean.valueOf(restClient.deleteRequest(String.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
