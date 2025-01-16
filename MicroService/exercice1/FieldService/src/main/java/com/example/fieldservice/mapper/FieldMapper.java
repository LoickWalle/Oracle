package com.example.fieldservice.mapper;

import com.example.commondto.field.FieldDTO;
import com.example.fieldservice.entities.Field;

public class FieldMapper {

    public static Field FieldDTOToEntity(FieldDTO fieldDTO) {
        Field field = new Field();
        field.setName(fieldDTO.getName());
        field.setCoefficient(fieldDTO.getCoefficient());
        return field;
    }

    public static FieldDTO FieldEntityToDTO(Field field) {
        FieldDTO fieldDTO = new FieldDTO();
        fieldDTO.setName(field.getName());
        fieldDTO.setCoefficient(field.getCoefficient());
        return fieldDTO;
    }
}
