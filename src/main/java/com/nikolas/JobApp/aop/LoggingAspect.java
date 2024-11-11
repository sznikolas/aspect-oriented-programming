package com.nikolas.JobApp.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class-name.method-name(args)
      @Before("execution(* com.nikolas.JobApp.service.JobService.getAllJobs(..))" )
//    @Before("execution(* com.nikolas.JobApp.service.JobService.*(..))" )
    public void logMethodCall(JoinPoint jp){
        LOGGER.info("Method called " + jp.getSignature().getName());
    }

    @After("execution(* com.nikolas.JobApp.service.JobService.getAllJobs(..))" )
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method executed " + jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.nikolas.JobApp.service.JobService.getAllJobs(..))" )
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("Method has some issues " + jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.nikolas.JobApp.service.JobService.getAllJobs(..))" )
    public void logMethodExecutedSuccess(JoinPoint jp){
        LOGGER.info("Method executed successfully " + jp.getSignature().getName());
    }


}
