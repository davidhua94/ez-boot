package com.david.system.admin.exception;

import com.david.core.exception.ServiceException;

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
