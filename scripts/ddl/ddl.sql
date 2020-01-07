/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.27 : Database - springboot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboot` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springboot`;

/*Table structure for table `t_student` */

DROP TABLE IF EXISTS `t_student`;

CREATE TABLE `t_student` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名字',
  `age` int(3) unsigned DEFAULT NULL COMMENT '年龄',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '简介',
  `create_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COMMENT='学生表, 测试用';

/*Table structure for table `t_system_admin` */

DROP TABLE IF EXISTS `t_system_admin`;

CREATE TABLE `t_system_admin` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员名称',
  `password` varchar(63) NOT NULL DEFAULT '' COMMENT '管理员密码',
  `last_login_ip` varchar(63) DEFAULT '' COMMENT '最近一次登录IP地址',
  `last_login_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近一次登录时间',
  `avatar` varchar(255) DEFAULT '''' COMMENT '头像图片',
  `enabled` tinyint(1) DEFAULT '1' COMMENT '逻辑删除',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

/*Table structure for table `t_system_admin_role` */

DROP TABLE IF EXISTS `t_system_admin_role`;

CREATE TABLE `t_system_admin_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `admin_id` int(10) unsigned NOT NULL COMMENT '管理员id',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_system_admin_role` */

/*Table structure for table `t_system_permission` */

DROP TABLE IF EXISTS `t_system_permission`;

CREATE TABLE `t_system_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `permission_type` enum('menu','button') DEFAULT NULL COMMENT '权限类型',
  `permission` varchar(63) DEFAULT NULL COMMENT '权限',
  `url` varchar(63) DEFAULT NULL COMMENT '请求url',
  `method` varchar(8) DEFAULT NULL COMMENT '请求方法',
  `parent_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '父id(菜单上级ID)',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '逻辑删除',
  `create_name` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_name` varchar(32) DEFAULT NULL COMMENT '修改人',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `t_system_permission` */

/*Table structure for table `t_system_role` */

DROP TABLE IF EXISTS `t_system_role`;

CREATE TABLE `t_system_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(63) NOT NULL COMMENT '角色名称',
  `description` varchar(512) DEFAULT NULL COMMENT '角色描述',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `create_name` varchar(32) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_name` varchar(32) NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Table structure for table `t_system_role_permission` */

DROP TABLE IF EXISTS `t_system_role_permission`;

CREATE TABLE `t_system_role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `role_id` int(11) unsigned NOT NULL COMMENT '角色id',
  `permission_id` int(11) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_system_role_permission` */

/*Table structure for table `t_system_timetask` */

DROP TABLE IF EXISTS `t_system_timetask`;

CREATE TABLE `t_system_timetask` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `task_name` varchar(64) NOT NULL COMMENT '任务名称',
  `task_class_name` varchar(128) NOT NULL COMMENT '执行任务的类全限定名',
  `cron_expression` varchar(32) NOT NULL COMMENT 'cron表达式',
  `enabled` tinyint(1) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用该定时任务',
  `started` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否正在执行',
  `create_name` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_name` varchar(64) DEFAULT NULL COMMENT '修改人',
  `update_time` varchar(64) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='定时任务表';

/*Data for the table `t_system_timetask` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/* 添加字段 */
alter table t_system_timetask
add column server varchar(32) not null comment 'job执行的server'
after cron_expression;