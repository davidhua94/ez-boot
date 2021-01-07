package com.ezboot.core.exception;

import com.ezboot.core.base.MessageCode;

/**
 * @author David hua
 * @date 2020-01-05 17:39:40
 */
public class AccessDeniedException extends ServiceException {

    public AccessDeniedException () {
        super(MessageCode.ACCESS_DENIED);
    }
}
