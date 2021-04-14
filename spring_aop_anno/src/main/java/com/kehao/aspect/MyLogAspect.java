package com.kehao.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyLogAspect {

    @Pointcut("execution(* com..*.*(..))")
    private void pointcut(){}

    @Before("pointcut()")
    public void logBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logBefore]");
    }

    @AfterReturning("pointcut()")
    public void logAfterReturn(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logAfterReturn]");
    }

    @AfterThrowing("pointcut()")
    public void logAfterThrowException(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logAfterThrowException]");
    }

    @After("pointcut()")
    public void logAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logAfter]");
    }
}
