package com.example.classservice.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ElementCollection
    private List<UUID> studentsID;
    private UUID teacherID;

    public Class() {
    }

    public Class(List<UUID> studentsID, UUID teacherID) {
        this.studentsID = studentsID;
        this.teacherID = teacherID;
    }

    public UUID getId() {
        return id;
    }

    public List<UUID> getStudentsID() {
        return studentsID;
    }

    public UUID getTeacherID() {
        return teacherID;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStudentsID(List<UUID> studentsID) {
        this.studentsID = studentsID;
    }

    public void setTeacherID(UUID teacherID) {
        this.teacherID = teacherID;
    }
}
