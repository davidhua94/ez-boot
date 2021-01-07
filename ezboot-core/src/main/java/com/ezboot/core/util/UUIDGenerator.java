package com.ezboot.core.util;

import java.util.UUID;

/**
 * @author David hua
 * @date 2019-08-18 21:37
 */
public class UUIDGenerator {

    public static String randomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * TODO 先暂时这样实现
     * @return
     */
    public static String shortStr() {
        return randomUUID().substring(0,10);
    }
}
