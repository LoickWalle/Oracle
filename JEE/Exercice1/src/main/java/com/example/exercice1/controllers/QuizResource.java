package com.example.exercice1.controllers;

import com.example.exercice1.models.Quiz;
import com.example.exercice1.services.QuizService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Path("/quizzes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizResource {

    private QuizService quizService;

    @Inject
    public QuizResource(QuizService quizService) {
        this.quizService = quizService;
    }

    @GET
    public List<Quiz> getQuiz() {
        return quizService.getQuizzes();
    }

    @GET
    @Path("/{id}")
    public Quiz getQuizByID(@PathParam("id") String id) {
        return quizService.getQuizByID(UUID.fromString(id));
    }

    @GET
    @Path("/{id}/results")
    public HashMap<String, Integer> getResultQuizByID(@PathParam("id") String id) {
        return quizService.getResultQuiz(UUID.fromString(id));
    }

    @POST
    public Quiz createQuiz(Quiz quiz) {
        return quizService.saveQuiz(quiz);
    }

    @POST
    @Path("/{id}/play")
    public HashMap<String, Integer> play(@PathParam("id") String id, @QueryParam("name") String name, List<String> answers) {
        return quizService.playQuiz(UUID.fromString(id), name, answers);
    }

    @DELETE
    @Path("/{id}")
    public String deleteQuizByID(@PathParam("id") String id) {
        return quizService.deleteQuiz(UUID.fromString(id));
    }
}
