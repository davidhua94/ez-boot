package com.ezboot.core;

import lombok.Getter;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author David hua
 * @date 2019-12-19 22:57:51
 */
@Getter
public class Config {
    private static final String DEFAULT_CONFIG_PATH = "generate";

    private String jdbcUrl;

    private String jdbcUser;

    private String password;

    private String driverClass;

    private String basePackage;

    private String dbTablePrefix;

    private List<GeneratorType> generatorTypes;

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
        this.basePackage = resource.getString("basePackage");
        this.dbTablePrefix = resource.getString("dbTablePrefix");
    }
}
