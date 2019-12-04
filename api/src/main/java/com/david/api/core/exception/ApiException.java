package com.david.api.core.exception;

import com.david.core.exception.ServiceException;

/**
 * @author David hua
 * @date 2019-08-03 21:57
 * API 基础异常
 */
public class ApiException extends ServiceException {

    public ApiException(int code) {
        super(code);
    }

    public ApiException(int code, String message) {
        super(code, message);
    }
}
