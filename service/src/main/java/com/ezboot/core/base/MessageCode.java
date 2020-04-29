package com.ezboot.core.base;

import java.io.Serializable;

/**
 * @author David hua
 * @date 2019-08-03 21:41
 * 基本状态码
 * 其他模块可继承这个
 * TODO 业务异常code应该与Http状态码分开
 * 0开头的是系统基础异常
 */
public abstract class MessageCode implements Serializable {
    public static final String SUCCESS = "00000";
    public static final String INVALID_PARAMS = "40000";
    public static final String UNAUTHORIZED = "401";
    public static final String NOT_FOUND = "404";
    public static final String INTERNAL_SERVER_ERROR = "99999";

    public static final String ACCESS_DENIED = "00001";

    /**
     * 其余按模块分配
     * A0001
     * A 代表system
     * 0代表admin
     * 1代表role
     * 01 代表具体的错误
     * 例如：管理员登陆失败 10001
     *      管理员不存在 10002
     *      角色不存在 10101
     *
     */
}
