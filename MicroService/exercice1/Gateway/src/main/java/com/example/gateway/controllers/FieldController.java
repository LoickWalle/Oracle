package com.example.gateway.controllers;

import com.example.commondto.field.FieldDTO;
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
@RequestMapping(Path.FIELD_ENDPOINT)
public class FieldController {
    private final ObjectMapper om;

    public FieldController(ObjectMapper objectMapper) {
        this.om = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.FIELD_API_URL);
        String response = restClient.getRequest(String.class);
        List<FieldDTO> fields = om.readValue(response, new TypeReference<>() {});
        return new ResponseEntity<>(fields, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable String id) throws JsonProcessingException {
        RestClient<String> restClient = new RestClient<>(Path.FIELD_API_URL+"/"+id);
        String response = restClient.getRequest(String.class);
        FieldDTO field = om.readValue(response, FieldDTO.class);
        return new ResponseEntity<>(field, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FieldDTO> createField(@RequestBody FieldDTO fieldDTO) throws JsonProcessingException {
        RestClient<FieldDTO> restClient = new RestClient<>(Path.FIELD_API_URL);
        FieldDTO fieldDTOResponse = restClient.postRequest(om.writeValueAsString(fieldDTO), FieldDTO.class);
        return new ResponseEntity<>(fieldDTOResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteField(@PathVariable String id) {
        RestClient<String> restClient = new RestClient<>(Path.FIELD_API_URL+"/"+id);
        Boolean response = Boolean.valueOf(restClient.deleteRequest(String.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
