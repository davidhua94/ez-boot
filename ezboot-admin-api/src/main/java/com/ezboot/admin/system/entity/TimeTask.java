package com.ezboot.admin.system.entity;

import com.ezboot.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author David hua
 * @date 2019-08-13 22:03
 * 定时任务实体
 */
@Data
@Entity
@Table(name = "t_system_timetask")
@EqualsAndHashCode(callSuper = false)
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

    @Column(name = "server")
    private String server;
}
