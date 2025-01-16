package com.example.fieldservice.controllers;

import com.example.commondto.field.FieldDTO;
import com.example.fieldservice.services.FieldService;
import org.example.commonconfig.endpoinds.Path;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Path.FIELD_ENDPOINT)
public class FieldController {
    private final FieldService fieldService;

    public FieldController(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @GetMapping
    public List<FieldDTO> getAllFields() {
        return fieldService.getAllFields();
    }

    @GetMapping("/{id}")
    public FieldDTO getFieldById(@PathVariable String id) {
        return fieldService.getFieldById(UUID.fromString(id));
    }

    @PostMapping
    public FieldDTO addField(@RequestBody FieldDTO fieldDTO) {
        return fieldService.addField(fieldDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteFieldById(@PathVariable String id) {
        return fieldService.deleteFieldById(UUID.fromString(id));
    }
}
