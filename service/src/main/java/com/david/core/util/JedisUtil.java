package com.david.core.util;

import com.david.core.ApplicationContextUtil;
import com.david.core.config.RedisConfig;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author David hua
 * @date 2019-08-18 21:45
 */
@Slf4j
public class JedisUtil {

    private static final int DEFAULT_EXPIRE_TIME = 60 * 30;

    private static JedisPool jedisPool;

    static {
        RedisConfig redisConfig = (RedisConfig) ApplicationContextUtil.getBean("redisConfig");
        jedisPool = new JedisPool(redisConfig.getHost());
    }

    public static boolean put(String key, String value, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String ret = jedis.set(key, value);
            if (expireTime > 0) {
                jedis.expire(key, expireTime);
            }
            return "ok".equals(ret);
        } catch (Exception e) {
            log.error("JedisUtil put() failed, error: {}", e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public static boolean put(String key, String val) {
        return put(key, val, DEFAULT_EXPIRE_TIME);
    }

    public static String get(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } catch (Exception e) {
            log.error("JedisUtil get() failed, key={}, error: {}", key, e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public static boolean exist(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            log.error("JedisUtil exist() failed, key={}, error: {}", key, e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public static void setExpire(String key, int expireTime) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            log.error("JedisUtil setExpire() failed, key={}, error: {}", key, e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static void del(String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            log.error("JedisUtil del() failed, key={}, error: {}", key, e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
