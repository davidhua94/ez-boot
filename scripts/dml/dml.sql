/*Data for the table `t_student` */

insert  into `t_student`(`id`,`name`,`age`,`description`,`create_name`,`create_time`,`update_name`,`update_time`) values
(4,'test',111,'????','123',NULL,NULL,NULL),
(5,'test',111,'????','123',NULL,NULL,NULL),
(6,'test',111,'????','123',NULL,NULL,NULL),
(7,'test',111,'????','123',NULL,NULL,NULL),
(8,'test',111,'测试数据','123',NULL,NULL,NULL),
(9,'test',111,'测试数据','123',NULL,NULL,NULL);

/*Data for the table `t_system_admin` */

insert  into `t_system_admin`(`id`,`username`,`password`,`last_login_ip`,`last_login_time`,`avatar`,`enabled`,`create_name`,`create_time`,`update_name`,`update_time`) values
(1,'test1','test1','','2019-08-16 22:39:04','\'',1,NULL,'2019-08-16 22:39:04',NULL,'2019-08-18 22:25:37');

/*Data for the table `t_system_role` */

insert  into `t_system_role`(`id`,`role_name`,`description`,`enabled`,`create_name`,`create_time`,`update_name`,`update_time`) values
(1,'管理员','管理员描述',0,'system','2019-11-10 12:11:07','system','2019-11-17 02:47:46'),
(2,'test-rol222e3434',NULL,0,'system','2019-11-10 12:11:07','test1','2019-11-17 03:58:18'),
(3,'管理员3','管理员描述3',1,'system','2019-11-10 12:11:07','system','2019-11-10 12:11:07'),
(4,'管理员4','管理员描述4',1,'system','2019-11-10 12:11:07','system','2019-11-10 12:11:07'),
(5,'开发','developer',1,'system','2019-11-17 02:45:46','system','2019-11-17 02:45:46'),
(6,'test-role','10',1,'test1','2019-11-17 03:55:22','test1','2019-11-17 03:55:22'),
(7,'test-rol222e','10',1,'test1','2019-11-17 03:56:42','test1','2019-11-17 03:56:42');