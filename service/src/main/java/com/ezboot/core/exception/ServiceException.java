package com.ezboot.core.exception;

import lombok.Getter;

/**
 * @author David hua
 * @date 2019-08-03 21:58
 * 本项目所有异常基类
 */
@Getter
public class ServiceException extends RuntimeException {
    protected String code;
    protected String message;

    public ServiceException(String code) {
        this.code = code;
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
