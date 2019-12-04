package com.david.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author David hua
 * @date 2019-08-09 22:12
 * 记录API访问日志切面
 */
@Slf4j
@Aspect
@Component
public class ApiAccessLog {

    @Autowired
    private HttpServletRequest request;

    @Pointcut("execution(public * com.david.controller.*.*.*(..))")
    public void log() {}

    @Around("log()")
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        /**
         * controller 方法执行前执行上面这段代码
         * 执行joinPoint.proceed()之后进入controller具体方法
         * controller 方法执行完执行joinPoint.proceed()下面的代码
         */
        try {
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            log.info("[{}] {}, ip={}, time={}ms", request.getMethod(),request.getRequestURL(),
                    request.getRemoteAddr(), endTime - startTime);
        }
    }
}
