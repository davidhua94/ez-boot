package com.ezboot.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
/** 默认只扫描当前类所在包(com.david.api)下的class */
@ComponentScan(basePackages = "com.ezboot")
@EnableJpaRepositories(basePackages = "com.ezboot")
@EntityScan(basePackages = "com.david")
/** 启用swagger2 */
@EnableSwagger2
/**
 * 启动基类
 */
public class ApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
