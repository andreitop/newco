package com.andreitop.newco.aspect;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    private int callNumber;

    @Before("com.andreitop.newco.aspect.PointcutContainer.repositoryFind()")
    public void beforeRepoFind(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " is about to be called");
    }

    @After("com.andreitop.newco.aspect.PointcutContainer.serviceMethod()")
    public void serviceCallsCounter(JoinPoint joinPoint) {
        callNumber++;
        logger.info("Number of calls to service: " + callNumber + ".");
    }

    @Around("com.andreitop.newco.aspect.PointcutContainer.repositorySave()")
    public void juniorMeasureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start;
        long end;

        start = System.nanoTime();
        joinPoint.proceed();
        end = System.nanoTime();

        logger.info(String.format("Execution time of method %s is %dms", joinPoint.getSignature().getName(), end - start));
    }

}
