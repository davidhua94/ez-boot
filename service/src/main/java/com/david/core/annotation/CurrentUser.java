package com.david.core.annotation;

import java.lang.annotation.*;

/**
 * @author David hua
 * @date 2019-11-10 16:06:01
 * 用于在controller请求参数里标注参数
 * 配合ArgumentResolver提前解析, 不需要每个方法都去获取一遍
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}
