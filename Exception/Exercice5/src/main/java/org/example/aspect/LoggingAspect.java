package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Around("@annotation(Log)")
    public Object logMethodInvocation(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.printf("Method %s called with arguments: %s", joinPoint.getSignature().getName(), Arrays.toString(args));

        Object result = joinPoint.proceed();

        System.out.printf("Method %s returned: %s", joinPoint.getSignature().getName(), result);

        return result;
    }
}
