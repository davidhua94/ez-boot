package com.ezboot.system.exception;

import com.ezboot.core.exception.ServiceException;

/**
 * Basic Exception for manage module
 */
public class AdminServiceException extends ServiceException {
    public AdminServiceException(String code) {
        super(code);
    }

    public AdminServiceException(String code, String message) {
        super(code, message);
    }
}
