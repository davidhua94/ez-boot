package com.ezboot.core.job;

import com.ezboot.core.ApplicationContextUtil;
import com.ezboot.system.timetask.entity.TimeTask;
import com.ezboot.system.timetask.service.TimeTaskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author David Hua
 * @date 2020/1/8
 * @desc
 */
@Slf4j
@Component
public class JobInitializer {

    @Value("${ez-boot.server}")
    private String currentServer;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private TimeTaskService taskService;

    public void init() throws ClassNotFoundException, SchedulerException {
        List<TimeTask> enabledTaskByServer = taskService.getEnabledTaskByServer(currentServer);
        if (CollectionUtils.isEmpty(enabledTaskByServer)) {
            log.info("No job need to be loaded , current server : {}", currentServer);
            return;
        }

        for (TimeTask timeTask : enabledTaskByServer) {
            if (timeTask.getServer().equals(currentServer)
                && timeTask.isEnabled()) {
                Scheduler scheduler = schedulerFactoryBean.getScheduler();

                Class clazz = Class.forName(timeTask.getTaskClassName());
                JobDetail jobDetail = JobBuilder.newJob(clazz)
                        .withIdentity(timeTask.getTaskName()).build();

                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(timeTask.getCronExpression());

                Trigger trigger = TriggerBuilder.newTrigger()
                        .withIdentity(timeTask.getTaskName())
                        .withSchedule(cronScheduleBuilder)
                        .forJob(jobDetail).build();

                scheduler.scheduleJob(jobDetail, trigger);
                log.debug("load task [{}] success", timeTask.getTaskName());
            }
        }
    }
}
