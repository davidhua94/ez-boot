package com.ezboot.system.admin.exception;

import com.ezboot.core.exception.ServiceException;

/**
 * @author David hua
 * @date 2019-08-18 21:27
 */
public class LoginException extends ServiceException {
    public LoginException(int code, String message) {
        super(code, message);
    }

    public LoginException(int code) {
        super(code);
    }
}
