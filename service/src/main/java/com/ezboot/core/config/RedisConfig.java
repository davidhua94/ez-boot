package com.ezboot.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author David hua
 * @date 2019-11-10 13:54:03
 */
@Configuration("redisConfig")
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private String host;
}
