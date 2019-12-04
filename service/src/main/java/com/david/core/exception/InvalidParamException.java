package com.david.core.exception;

import com.david.core.base.MessageCode;

/**
 * @author David hua
 * @date 2019-08-16 23:18
 */
public class InvalidParamException extends ServiceException {

    public InvalidParamException(String message) {
        super(MessageCode.INVALID_PARAMS, message);
    }
}
