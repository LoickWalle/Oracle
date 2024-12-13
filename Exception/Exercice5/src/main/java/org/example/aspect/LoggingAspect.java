package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(org.example.annotation.Log)")
    public void loggingPointCut(){

    }

    @Around("loggingPointCut()")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("Logging : ");
        System.out.printf("Method %s called with arguments: %s\n", joinPoint.getSignature().getName(), Arrays.toString(args));

        Object result = joinPoint.proceed();

        System.out.printf("Method %s returned: %s\n", joinPoint.getSignature().getName(), result);

        return result;
    }
}
