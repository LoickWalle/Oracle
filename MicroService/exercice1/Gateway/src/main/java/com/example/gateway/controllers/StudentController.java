package com.example.gateway.controllers;

import com.example.commondto.student.StudentDTO;
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
@RequestMapping(Path.STUDENT_ENDPOINT)
public class StudentController {
    private final ObjectMapper om;

    public StudentController(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudent() throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.STUDENT_API_URL);
        String response = restClient.getRequest(String.class);
        List<StudentDTO> students = om.readValue(response, new TypeReference<>() {});
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.STUDENT_API_URL+"/"+id);
        String response = restClient.getRequest(String.class);
        StudentDTO students = om.readValue(response, StudentDTO.class);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws JsonProcessingException {
        RestClient<StudentDTO> restClient = new RestClient<>(Path.STUDENT_API_URL);
        StudentDTO studentDTOResponse = restClient.postRequest(om.writeValueAsString(studentDTO), StudentDTO.class);
        return new ResponseEntity<>(studentDTOResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> modifyStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) throws JsonProcessingException {
        RestClient<StudentDTO> restClient = new RestClient<>(Path.STUDENT_API_URL+"/"+id);
        StudentDTO studentDTOResponse = restClient.putRequest(om.writeValueAsString(studentDTO), StudentDTO.class);
        return new ResponseEntity<>(studentDTOResponse, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.STUDENT_API_URL+"/"+id);
        Boolean response = Boolean.valueOf(restClient.deleteRequest(String.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
