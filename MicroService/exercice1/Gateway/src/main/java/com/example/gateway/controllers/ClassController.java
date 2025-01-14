package com.example.gateway.controllers;

import com.example.gateway.dtos.ClassDTO;
import com.example.gateway.dtos.ClassRequestDTO;
import com.example.gateway.utils.RestClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/class")
public class ClassController {
    private final ObjectMapper om;

    public ClassController(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClassDTO>> getAllClasses() throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>("http://localhost:8084/api/class");
        String response = restClient.getRequest(String.class);
        List<ClassDTO> classes = om.readValue(response, new TypeReference<>() {});
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDTO> getStudentById(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>("http://localhost:8084/api/class/"+id);
        String response = restClient.getRequest(String.class);
        ClassDTO classDTO = om.readValue(response, ClassDTO.class);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClassDTO> createClass(@RequestBody ClassRequestDTO classRequestDTO) throws JsonProcessingException {
        RestClient<ClassDTO> restClient = new RestClient<>("http://localhost:8084/api/class");
        ClassDTO classDTOResponse = restClient.postRequest(om.writeValueAsString(classRequestDTO), ClassDTO.class);
        return new ResponseEntity<>(classDTOResponse, HttpStatus.CREATED);
    }
}
