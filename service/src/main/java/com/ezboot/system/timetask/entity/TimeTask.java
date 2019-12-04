package com.ezboot.system.timetask.entity;

import com.ezboot.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author David hua
 * @date 2019-08-13 22:03
 * 定时任务实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity(name = "t_system_timetask")
public class TimeTask extends UpdatedEntity {

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_class_name")
    private String taskClassName;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "started")
    private boolean started;
}
