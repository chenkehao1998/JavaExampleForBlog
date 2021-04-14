package com.kehao.aspect;

import org.aspectj.lang.JoinPoint;

public class MyLogAspect {


    public void logBefore(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logBefore]");
    }

    public void logAfterReturn(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logAfterReturn]");
    }

    public void logAfterThrowException(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logAfterThrowException]");
    }

    public void logAfter(JoinPoint joinPoint) throws Throwable {
        System.out.println("[logAfter]");
    }
}
