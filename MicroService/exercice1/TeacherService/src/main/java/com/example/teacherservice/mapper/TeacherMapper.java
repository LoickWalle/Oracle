package com.example.teacherservice.mapper;

import com.example.commondto.teacher.TeacherDTO;
import com.example.teacherservice.entities.Teacher;

public class TeacherMapper {

    public static Teacher TeacherDTOToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacher.setBirthday(teacherDTO.getBirthDate());
        return teacher;
    }

    public static TeacherDTO EntityToTeacherDTO(Teacher teacher) {
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setBirthDate(teacher.getBirthday());
        return teacherDTO;
    }
}
