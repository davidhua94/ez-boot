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

    /**
     * 系统默认时间显示格式
     */
    String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 系统默认日期显示格式
     */
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 系统默认时区
     */
    String DEFAULT_TIMEZONE = "GMT+8";
}
