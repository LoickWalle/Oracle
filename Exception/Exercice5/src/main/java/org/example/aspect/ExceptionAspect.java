package org.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @Pointcut("@annotation(ExceptionHandled)")
    public void excepPointCut(){

    }

    @AfterThrowing(value = "excepPointCut()",throwing = "ex")
    public void afterThrowing (JoinPoint joinPoint, Exception ex){

        System.out.println("Exception message in Advice "+ex.getMessage()+ " "+ joinPoint.getSignature().getName());
    }
}