package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Pointcut("@annotation(org.example.annotation.Performance)")
    public void performancePointCut(){

    }

    @Before("performanceMethods()")
    public void startTimer() {

    }

    @Around("performancePointCut()")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();
        long duration = end - start;

        System.out.println("Performance : ");
        System.out.printf("Execution of %s took %d ms\n", joinPoint.getSignature().getName(), duration);

        return result;
    }
}
