package com.example.exercice1.models;

import java.util.List;

public class Question {
    private String question;
    private List<String> possibleAnswers;
    private String answer;

    public Question() {
    }

    public Question(String question, List<String> possibleAnswers, String answer) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }
}
