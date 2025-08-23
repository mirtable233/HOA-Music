package org.csu.hoaserver.aspect;

import enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.csu.hoaserver.annotation.AutoFill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutoFillAspect {

    @Pointcut("execution(* org.csu.hoaserver.dao.*.*(..)) && @annotation(org.csu.hoaserver.annotation.AutoFill)")
    public void autoFillPointCut() {}

    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("Auto fill pointcut");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();
        Object[] args = joinPoint.getArgs();
        if (args==null || args.length==0) {
            return;
        }
        Object object = args[0];
        LocalDateTime localDateTime = LocalDateTime.now();
        if (operationType == OperationType.INSERT) {
            try {
            log.info("Insert operation");
            Method setCreateTime = object.getClass().getDeclaredMethod("setCreateTime", LocalDateTime.class);
            Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
            setCreateTime.invoke(object,localDateTime);
            setUpdateTime.invoke(object,localDateTime);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (operationType == OperationType.UPDATE) {
            try{
            log.info("Update operation");
                Method setUpdateTime = object.getClass().getDeclaredMethod("setUpdateTime", LocalDateTime.class);
                setUpdateTime.invoke(object,localDateTime);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    }
