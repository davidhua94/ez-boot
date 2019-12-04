package com.ezboot.core.constant;

/**
 * @author David hua
 * @date 2019-08-16 22:09
 * management 全局常量
 */
public interface GlobalConstants {
    ThreadLocal<String> traceIdThreadLocal = new ThreadLocal<>();

    String ADMIN_TOKEN_KEY = "X-Admin-Token";

    /**
     * 管理员token过期时间, 单位second, 默认30分钟
     */
    int ADMIN_TOKEN_EXPIRE = 30 * 60;
}
