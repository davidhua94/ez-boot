package com.david.api.core.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author David hua
 * @date 2019-08-03 16:47
 * 全局统一异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e) {
        log.error("System error", e);
    }
}
