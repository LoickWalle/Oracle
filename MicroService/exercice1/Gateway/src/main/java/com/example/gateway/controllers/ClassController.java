package com.example.gateway.controllers;

import com.example.commondto.classes.ClassRequestDTO;
import com.example.commondto.classes.ClassResponseDTO;
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
@RequestMapping(Path.CLASS_ENDPOINT)
public class ClassController {
    private final ObjectMapper om;

    public ClassController(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClassResponseDTO>> getAllClasses() throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.CLASS_API_URL);
        String response = restClient.getRequest(String.class);
        List<ClassResponseDTO> classes = om.readValue(response, new TypeReference<>() {});
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> getStudentById(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.CLASS_API_URL+"/"+id);
        String response = restClient.getRequest(String.class);
        ClassResponseDTO classDTO = om.readValue(response, ClassResponseDTO.class);
        return new ResponseEntity<>(classDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClassResponseDTO> createClass(@RequestBody ClassRequestDTO classRequestDTO) throws JsonProcessingException {
        RestClient<ClassResponseDTO> restClient = new RestClient<>(Path.CLASS_API_URL);
        ClassResponseDTO classDTOResponse = restClient.postRequest(om.writeValueAsString(classRequestDTO), ClassResponseDTO.class);
        return new ResponseEntity<>(classDTOResponse, HttpStatus.CREATED);
    }
}
