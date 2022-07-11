package com.smi.parents_place.aspects;

import ch.qos.logback.classic.Logger;
import com.smi.parents_place.controller.MyRESTController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyRESTController.class);

    @Around("execution(* com.smi.parents_place.DAO.ParticipantDAOImpl.*(..))")
    public Object aroundAllMethodsInDAOLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        logger.debug("Begin " + methodSignature.getName());
        Object object = proceedingJoinPoint.proceed();
        logger.debug("End " + methodSignature.getName());
        return object;
    }


}
