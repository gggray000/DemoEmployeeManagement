package com.employeeManagement.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.employeeManagement.demo.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.employeeManagement.demo.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.employeeManagement.demo.dao.*.*(..))")
    private void forDAOPackage(){}

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow(){}

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint){

        logger.info("=====>>> @Before on: " + joinPoint.getSignature().toShortString());

        Object[] args = joinPoint.getArgs();
        for(Object arg: args){
            logger.info("-----> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "result"
    )
    public void afterReturning(JoinPoint joinPoint, Object result){

        logger.info("=====>>> @AfterReturning on: " + joinPoint.getSignature().toShortString());

        logger.info("-----> result: " + result);
    }
}
