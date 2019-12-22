package com.ezboot.core;

import lombok.Getter;

import java.util.ResourceBundle;

/**
 * @author David hua
 * @date 2019-12-19 22:57:51
 */
@Getter
public class Config {
//    private static final String DEFAULT_CONFIG_PATH = "classpath:generate.properties";
    private static final String DEFAULT_CONFIG_PATH = "generate";

    private String jdbcUrl;

    private String jdbcUser;

    private String password;

    private String driverClass;

    public Config() {
        this(DEFAULT_CONFIG_PATH);
    }

    public Config(String configPath) {
        // 不需要.properties后缀
        ResourceBundle  resource = ResourceBundle.getBundle(configPath);
        this.jdbcUrl = resource.getString("jdbc.url");
        this.jdbcUser = resource.getString("jdbc.username");
        this.password = resource.getString("jdbc.password");
        this.driverClass = resource.getString("jdbc.driverClass");
    }
}
