package com.ezboot.test.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * @author David Hua
 * @date 2020/1/8
 * @desc
 */
@Slf4j
@Component
public class LogJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("current time : {}", System.currentTimeMillis());
    }
}
