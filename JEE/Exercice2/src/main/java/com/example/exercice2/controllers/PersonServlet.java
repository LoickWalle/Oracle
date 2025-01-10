package com.example.exercice2.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.exercice2.models.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "personServlet", value = "/person")
public class PersonServlet extends HttpServlet {
    private List<Person> persons;

    public void init() {
        persons = new ArrayList<>();
        persons.add(new Person("Delory", "Christophe", 25));
        persons.add(new Person("Walle", "Loick", 30));
        persons.add(new Person("Jaipas", "Dinspi", 404));
        persons.add(new Person("De Rives", "Geralt", 1000));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("persons", persons);
        getServletContext().getRequestDispatcher("/ma-liste.jsp").forward(request, response);
    }

    public void destroy() {
    }
}