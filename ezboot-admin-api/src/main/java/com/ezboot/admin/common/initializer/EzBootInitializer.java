package com.ezboot.admin.common.initializer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author David Hua
 * @date 2020/1/7
 * @desc 在应用启动完之后执行
 *      加载job
 *      初始化字典并加载到redis？
 */
@Slf4j
@Component
public class EzBootInitializer implements ApplicationRunner {

    @Autowired
    private JobInitializer jobInitializer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("ez-boot started success");
        // todo
        jobInitializer.init();
    }
}
