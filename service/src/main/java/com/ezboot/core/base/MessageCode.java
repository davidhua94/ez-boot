package com.ezboot.core.base;

import java.io.Serializable;

/**
 * @author David hua
 * @date 2019-08-03 21:41
 * 基本状态码
 * 其他模块可继承这个
 * TODO 业务异常code应该与Http状态码分开
 */
public abstract class MessageCode implements Serializable {
    public static final Integer SUCCESS = 200;
    public static final Integer INVALID_PARAMS = 400;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer NOT_FOUND = 404;
    public static final Integer INTERNAL_SERVER_ERROR = 500;

    /**
     * 其余按模块分配
     * 10001
     * 10 代表system
     * 0代表admin
     * 1代表role
     * 01 代表具体的错误
     * 例如：管理员登陆失败 10001
     *      管理员不存在 10002
     *      角色不存在 10101
     *
     */
}
