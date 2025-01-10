<%@ page import="com.example.exercice2.models.Person" %>
<jsp:useBean id="persons" type="java.util.ArrayList<com.example.exercice2.models.Person>" scope="request" />
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste de personnes</title>
    <%@include file="WEB-INF/bootstrapImport.html"%>
</head>
<body>
    <div class="container">
        <h1>Ma liste de personnes : </h1>

        <table class="table table-dark table-striped">
            <thead>
            <tr>
                <th scope="col">Nom</th>
                <th scope="col">Prenom</th>
                <th scope="col">Age</th>
            </tr>
            </thead>
            <tbody>
                <% for(Person p : persons) {%>
                <tr>
                    <td><%= p.getName() %></td>
                    <td><%= p.getFirstname() %></td>
                    <td><%= p.getAge() + " ans"%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </div>
</body>
</html>
