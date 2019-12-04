package com.david.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author David hua
 * @date 2019-08-16 23:27
 */
@Component
public class RedisUtil {
    /**
     * 单位s
     */
    private static final int DEFAULT_EXPIRE_TIME = 60 * 30;

    /**
     * 是否开启redis缓存  true开启   false关闭
     */
    @Value("${spring.redis.open: #{false}}")
    private boolean open;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;


    /**
     * 操作对象
     */
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 操作字符串
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired(required = false)
    private ValueOperations<String, String> valueOperations;

    public final static Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();

    public void put(String key, String value, int timeout) {
        if (!open) {
            return;
        }
        try {
            valueOperations.set(key, toJson(value));

        } catch (Exception e0) {
            System.out.println(e0.getMessage());
        }
        if (timeout != NOT_EXPIRE) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        if (!open) {
            return;
        }
        try {
            valueOperations.set(key, toJson(value));
        } catch (Exception e0) {
            System.out.println(e0.getMessage());
        }
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object) {
        if (!open) {
            return null;
        }

        if (object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }

        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz) {
        if (!open) {
            return null;
        }

        return gson.fromJson(json, clazz);
    }

//    public static boolean put(String key, String val) {
//        return put(key, val, DEFAULT_EXPIRE_TIME);
//    }

    public static String get(String ket) {
        return null;
    }

    public static boolean exist(String key) {
        return false;
    }


}
