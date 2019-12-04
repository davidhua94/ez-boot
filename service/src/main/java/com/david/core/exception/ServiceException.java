package com.david.core.exception;

import lombok.Getter;

/**
 * @author David hua
 * @date 2019-08-03 21:58
 * 本项目所有异常基类
 */
@Getter
public class ServiceException extends RuntimeException {
    protected int code;
    protected String message;

    public ServiceException(int code) {
        this.code = code;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
