package com.ezboot.core.base;

import java.io.Serializable;

/**
 * @author David hua
 * @date 2019-08-03 21:41
 * 状态码一共5位  基本状态码为5个数字, 业务相关状态码第一位是字母表示所在模块, 其余4位为数字表示具体错误
 * 其他模块可继承这个
 */
public abstract class MessageCode implements Serializable {
    public static final String SUCCESS = "00000";
    public static final String INVALID_PARAMS = "40000";
    public static final String UNAUTHORIZED = "40100";
    public static final String NOT_FOUND = "40400";
    public static final String INTERNAL_SERVER_ERROR = "99999";

    public static final String ACCESS_DENIED = "00001";
}
