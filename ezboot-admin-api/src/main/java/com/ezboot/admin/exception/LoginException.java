package com.ezboot.admin.exception;

import com.ezboot.core.exception.ServiceException;

/**
 * @author David hua
 * @date 2019-08-18 21:27
 */
public class LoginException extends ServiceException {
    public LoginException(String code, String message) {
        super(code, message);
    }

    public LoginException(String code) {
        super(code);
    }
}
