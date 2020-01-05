package com.ezboot.api.core.exception;

import com.ezboot.core.exception.ServiceException;

/**
 * @author David hua
 * @date 2019-08-03 21:57
 * API 基础异常
 */
public class ApiException extends ServiceException {

    public ApiException(String code) {
        super(code);
    }

    public ApiException(String code, String message) {
        super(code, message);
    }
}
