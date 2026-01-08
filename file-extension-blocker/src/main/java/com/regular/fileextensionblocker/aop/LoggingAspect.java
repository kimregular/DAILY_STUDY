package com.regular.fileextensionblocker.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.regular.fileextensionblocker.controller..*(..))")
    public void controllerMethods() {}

    @Pointcut("execution(* com.regular.fileextensionblocker.service..*(..))")
    public void serviceMethods() {}

    @Around("controllerMethods() || serviceMethods()")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        if (className.contains("ApiController") || className.contains("Service")) {
            if (methodName.equals("setFixedBlocked") || methodName.equals("isBlocked")) {
                log.trace("[{}] {} starts with args: {}", className, methodName, Arrays.toString(args));
            } else {
                log.info("[{}] {} starts with args: {}", className, methodName, Arrays.toString(args));
            }
        }

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long executionTime = System.currentTimeMillis() - start;

            if (className.contains("ApiController") || className.contains("Service")) {
                if (methodName.equals("setFixedBlocked") || methodName.equals("isBlocked")) {
                    log.trace("[{}] {} finished in {}ms", className, methodName, executionTime);
                } else {
                    log.info("[{}] {} finished in {}ms", className, methodName, executionTime);
                }
            }
            return result;
        } catch (Throwable e) {
            log.error("[{}] {} failed with exception: {}", className, methodName, e.getMessage());
            throw e;
        }
    }
}
