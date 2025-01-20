package org.example.exercice5.controller.taskController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class TaskRouter {

    @Bean
    public RouterFunction<ServerResponse> productRoutes(TaskHandler taskHandler) {
        return route(GET("/api/tasks"), taskHandler::getAllTasks)
                .andRoute(GET("/api/tasks/{id}"), taskHandler::getTaskByID)
                .andRoute(POST("/api/tasks"), taskHandler::createTask)
                .andRoute(PUT("/api/tasks/{id}"), taskHandler::updateTask)
                .andRoute(DELETE("/api/tasks/{id}"), taskHandler::deleteTask);
    }
}
