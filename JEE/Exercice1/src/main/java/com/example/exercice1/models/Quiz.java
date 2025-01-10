package com.example.exercice1.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Quiz {
    UUID id;
    List<Question> questions = new ArrayList<>();
    HashMap<String, Integer> scores = new HashMap<>();

    public Quiz() {
    }

    public Quiz(List<Question> questions) {
        this.id = UUID.randomUUID();
        this.questions = questions;
    }

    public UUID getId() {
        return id;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public HashMap<String, Integer> getScores() {
        return scores;
    }

    public void setScores(String player, int score) {
        scores.put(player, score);
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", questions=" + questions +
                ", scores=" + scores +
                '}';
    }
}
