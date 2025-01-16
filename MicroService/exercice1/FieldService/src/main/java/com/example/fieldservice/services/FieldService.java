package com.example.fieldservice.services;

import com.example.commondto.field.FieldDTO;
import com.example.fieldservice.entities.Field;
import com.example.fieldservice.mapper.FieldMapper;
import com.example.fieldservice.repositories.FieldRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FieldService {
    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
        this.fieldRepository.save(new Field("Math", 5));
        this.fieldRepository.save(new Field("English", 3));
        this.fieldRepository.save(new Field("Physics", 4));
        this.fieldRepository.save(new Field("History", 3));
    }

    public List<FieldDTO> getAllFields() {
        return fieldRepository.findAll().stream()
                .map(FieldMapper::FieldEntityToDTO)
                .toList();
    }

    public FieldDTO getFieldById(UUID fieldId) {
        return fieldRepository.findById(fieldId)
                .map(FieldMapper::FieldEntityToDTO)
                .orElse(null);
    }

    public FieldDTO addField(FieldDTO fieldDTO) {
        Field field = FieldMapper.FieldDTOToEntity(fieldDTO);
        Field fieldSaved = fieldRepository.save(field);
        return FieldMapper.FieldEntityToDTO(fieldSaved);
    }

    public boolean deleteFieldById(UUID id) {
        if(this.fieldRepository.existsById(id)) {
            this.fieldRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
