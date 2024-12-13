package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
public class ExceptionAspect {

    @Pointcut("@annotation(org.example.annotation.ExceptionHandled)")
    public void excepPointCut(){

    }

    @AfterThrowing(value = "excepPointCut()",throwing = "ex")
    public void afterThrowing (JoinPoint joinPoint, Exception ex){
        String message = "Exception reçu à " + LocalDateTime.now() + " : "+ex.getMessage()+ " appelé par "+ joinPoint.getSignature().getName();
        System.out.println(message);

        File file = new File("exceptionLog.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}