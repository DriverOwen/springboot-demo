package com.xowen.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.ObjectError;

@Slf4j
@Component
@Aspect
public class TimeAspect {

    @Pointcut("execution(* com.xowen.service.*.*(..))")
    private void pc(){}

    @Around("pc()") // 切入点表达式 也就是对哪些方法进行调用原始方法
    private Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录开始时间
        long begin = System.currentTimeMillis();
        // 2. 调用原始方法运行
        Object result = joinPoint.proceed();
        // 3. 记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        log.info(joinPoint.getSignature()+"方法执行耗时：{}ms",end-begin);
        return result;
    }

    @After("pc()")
    private void after(){
        log.info("after---");
    }

    @Before("pc()")
    private void before(){
        log.info("before---");
    }
}
