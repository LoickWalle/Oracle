package com.example.exercice1.services;

import com.example.exercice1.models.Question;
import com.example.exercice1.models.Quiz;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

@ApplicationScoped
public class QuizService {
    List<Quiz> quizzes = new ArrayList<>();

    public QuizService() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Quelle est la capitale de l'Irlande ?",
                new ArrayList<>(Arrays.asList("Londre", "Dublin", "Tokyo", "Minas Tirith")),
                "Dublin"));

        Quiz quiz = new Quiz(questions);
        quizzes.add(quiz);
    }

    public List<Quiz> getQuizzes(){
        return quizzes;
    }

    public Quiz getQuizByID(UUID id){
        for(Quiz quiz : quizzes){
            if(quiz.getId().equals(id)){
                return quiz;
            }
        }
        return null;
    }

    public HashMap<String, Integer> getResultQuiz(UUID id){
        Quiz quiz = getQuizByID(id);
        if(quiz == null)
            return null;

        return quiz.getScores();
    }

    public HashMap<String, Integer> playQuiz(UUID id, String name, List<String> answers){
        Quiz quiz = getQuizByID(id);
        if (quiz == null)
            return null;

        int score = 0;
        int index = 0;

        if(answers.size() != quiz.getQuestions().size()){
            System.out.println("Vous n'avez pas répondu à toutes les questions !");
            return null;
        }

        for(Question question : quiz.getQuestions()){
            if(question.getAnswer().equals(answers.get(index++))){
                score++;
            }
        }
        quiz.setScores(name, score);
        return quiz.getScores();
    }

    public Quiz saveQuiz(Quiz quiz) {
        quiz.setId(UUID.randomUUID());
        quizzes.add(quiz);
        return quiz;
    }

    public String deleteQuiz(UUID id) {
        Quiz quiz = getQuizByID(id);
        if (quiz != null) {
            quizzes.remove(quiz);
        }
        return "le quiz a été supprimé";
    }
}
