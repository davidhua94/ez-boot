package com.ezboot.aspect;

import com.ezboot.core.ApiResult;
import com.ezboot.core.constant.GlobalConstants;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author David hua
 * @date 2019-08-16 22:47
 * 获取此次请求traceId, 放回response
 */
@Component
@Slf4j
@Aspect
public class TraceIdAspect {
    @Pointcut("execution(* com.ezboot.*..*Controller.*(..))")
    public void pointcut() {

    }

    @AfterReturning(returning = "ret", pointcut = "pointcut()")
    public void afterReturning(Object ret) {
        if (ret instanceof ApiResult) {
            ((ApiResult) ret).setRequestId(GlobalConstants.traceIdThreadLocal.get());
        } else {
            log.warn("Invalid Response Type, {}", ret);
        }
    }
}
