package com.ezboot.core.annotation;

import com.ezboot.core.base.entity.AbstractEntity;

import java.lang.annotation.*;

/**
 * @author David hua
 * @date 2019-12-29 22:40:56
 * 用于校验唯一值, 如唯一用户名, 配合validator使用
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UniqueField {
    /**
     * 列名
     * @return
     */
    String column();

    /**
     * 实体类型
     * @return
     */
    Class<? extends AbstractEntity> type();


}
