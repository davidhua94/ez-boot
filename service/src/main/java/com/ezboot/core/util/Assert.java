package com.ezboot.core.util;

import com.ezboot.core.exception.InvalidParamException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author David hua
 * @date 2019-08-16 23:22
 * 简单参数校验
 */
public class Assert {
    public static void notBlank(String obj, String errMsg) {
        if (StringUtils.isBlank(obj)) {
            throw new InvalidParamException(errMsg);
        }
    }
}
