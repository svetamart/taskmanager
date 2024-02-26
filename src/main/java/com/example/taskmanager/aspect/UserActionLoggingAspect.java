package com.example.taskmanager.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
@Component
public class UserActionLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.taskmanager.service.TaskService.*(..)) || execution(* com.example.taskmanager.controller.TaskController.*(..))")
    public void serviceAndControllerMethods() {

    }

    @Around("serviceAndControllerMethods()")
    public Object logUserAction(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();
        Object[] methodArgs = joinPoint.getArgs();

        logger.info("User action in method {}: Params: {}", methodName, Arrays.toString(methodArgs));

        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            logger.info("User action in method {}: Result: {}. Execution Time: {} ms",
                    methodName, result, endTime - startTime);
            return result;
        } catch (Throwable e) {
            long endTime = System.currentTimeMillis();
            logger.error("Exception in method {}: {}. Execution Time: {} ms",
                    methodName, e.getMessage(), endTime - startTime);
            throw e;
        }
    }
}
