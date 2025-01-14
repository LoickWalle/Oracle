package com.example.classservice.controllers;

import com.example.classservice.dtos.ClassRequestDTO;
import com.example.classservice.dtos.ClassResponseDTO;
import com.example.classservice.services.ClassService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/class")
public class ClassController {

    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping
    public List<ClassResponseDTO> getAllClasses() throws JsonProcessingException {
        return classService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ClassResponseDTO getClassById(@PathVariable String id) throws JsonProcessingException {
        return classService.getClassById(UUID.fromString(id));
    }

    @PostMapping
    public ClassResponseDTO createClass(@RequestBody ClassRequestDTO classRequestDTO) throws JsonProcessingException {
        return classService.createClass(classRequestDTO);
    }

}
