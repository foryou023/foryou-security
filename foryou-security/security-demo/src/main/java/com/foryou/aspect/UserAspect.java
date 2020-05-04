package com.foryou.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class UserAspect {
    @Around("execution(* com.foryou.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }
        System.out.println("控制器方法执行前进行预处理...");
        long startTime = System.currentTimeMillis();
        Object proceed = pjp.proceed();
        System.out.println("耗时："+(System.currentTimeMillis()-startTime)+"毫秒");
        System.out.println("控制器方法执行后进行后处理...");
        return proceed;
    }
}
