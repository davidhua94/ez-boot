package com.ezboot.core.annotation;

import java.lang.annotation.*;

/**
 * @author David hua
 * @date 2020-01-05 17:22:44
 * 配合拦截器实现动态权限
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HasPermission {
    String needPermission() default "";
}
