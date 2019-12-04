
CREATE TABLE t_schedule_job (
  id int(11) unsined NOT NULL COMMENT 'pk',
  job_name varchar(32) COMMENT 'job名',
  job_group varchar(32) COMMENT 'job组',
  cron_expression varchar(32) COMMENT 'cron表达式',
  description varchar(256) COMMENT 'job描述',
  is_running tinyint(1) unsined NOT NULL DEFAULT 0 COMMENT '是否正在运行',
  run_server varchar(32) COMMENT '在哪台机器上执行',
  enabled tinyint(1) COMMENT '是否启用',
  create_name varchar(32) COMMENT '创建人',
  create_time datetime COMMENT '创建时间',
  update_name varchar(32) COMMENT '修改人',
  update_time DATETIME COMMENT '修改时间',
  PRIMARY key(id)
)engine=InnoDB comment='定时任务表'