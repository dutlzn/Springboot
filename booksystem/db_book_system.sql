/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.17-log : Database - db_book_system
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_book_system` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_book_system`;

/*Table structure for table `t_a_book_type` */

DROP TABLE IF EXISTS `t_a_book_type`;

CREATE TABLE `t_a_book_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `order_no` int(11) NOT NULL,
  `update_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_a_book_type` */

insert  into `t_a_book_type`(`id`,`create_date_time`,`name`,`order_no`,`update_date_time`) values (1,'2019-07-15 13:25:51','学习类',1,'2019-07-15 13:31:26'),(2,'2019-07-27 12:06:33','编程类',2,NULL);

/*Table structure for table `t_a_role` */

DROP TABLE IF EXISTS `t_a_role`;

CREATE TABLE `t_a_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `order_no` int(11) NOT NULL,
  `update_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_a_role` */

insert  into `t_a_role`(`id`,`create_date_time`,`name`,`order_no`,`update_date_time`) values (1,'2019-07-09 22:08:04','超级管理员',1,'2019-07-15 00:04:21'),(2,'2019-07-10 19:50:12','普通用户',2,'2019-07-13 23:22:57');

/*Table structure for table `t_a_user` */

DROP TABLE IF EXISTS `t_a_user`;

CREATE TABLE `t_a_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `order_no` int(11) NOT NULL,
  `pwd` varchar(200) DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `true_name` varchar(200) NOT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj7usi932axhbnhfmbtf3j6toh` (`role_id`),
  CONSTRAINT `FKj7usi932axhbnhfmbtf3j6toh` FOREIGN KEY (`role_id`) REFERENCES `t_a_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_a_user` */

insert  into `t_a_user`(`id`,`create_date_time`,`name`,`order_no`,`pwd`,`remark`,`true_name`,`update_date_time`,`role_id`) values (1,'2019-07-09 22:08:28','admin',1,'ba61ce8fa1e3725876e6363c76043c8d','备注','小明','2019-07-12 10:10:51',1);

/*Table structure for table `t_book` */

DROP TABLE IF EXISTS `t_book`;

CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL,
  `bianhao` varchar(20) DEFAULT NULL,
  `create_date_time` datetime DEFAULT NULL,
  `danjia` decimal(10,2) NOT NULL,
  `image_url` varchar(200) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `num` int(11) NOT NULL,
  `order_no` int(11) NOT NULL,
  `press` varchar(20) DEFAULT NULL,
  `update_date_time` datetime DEFAULT NULL,
  `book_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKik6nixtd03m92hoqfr3qw2ogg` (`book_type_id`),
  CONSTRAINT `FKik6nixtd03m92hoqfr3qw2ogg` FOREIGN KEY (`book_type_id`) REFERENCES `t_a_book_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_book` */

insert  into `t_book`(`id`,`author`,`bianhao`,`create_date_time`,`danjia`,`image_url`,`name`,`num`,`order_no`,`press`,`update_date_time`,`book_type_id`) values (2,'123','002','2019-07-27 12:09:50','123.00',NULL,'123',123,123,'12',NULL,1),(3,'324','34','2019-07-27 12:09:54','43.00',NULL,'24',324234,34,'4324',NULL,1);

/*Table structure for table `t_config` */

DROP TABLE IF EXISTS `t_config`;

CREATE TABLE `t_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `web_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_config` */

insert  into `t_config`(`id`,`web_name`) values (1,'图书管理系统');

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_date_time` datetime DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `t_member` */

insert  into `t_member`(`id`,`create_date_time`,`name`,`phone`,`pwd`,`true_name`) values (3,'2019-09-29 23:12:44','001','1234567891','001','小明'),(4,'2019-09-29 23:14:54','002','123','123','456');

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `div_id` varchar(50) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `order_no` int(11) NOT NULL,
  `p_id` int(11) DEFAULT NULL,
  `permissions` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`div_id`,`icon`,`name`,`order_no`,`p_id`,`permissions`,`state`,`type`,`url`) values (1,'user','&#xe66a;','基本维护',1,-1,'用户',0,0,''),(2,'user','&#xe67a; ','用户管理',1,1,'用户管理',1,0,'/houtai/user/manage'),(3,'book','&#xe609;','图书',2,-1,'图书',0,0,''),(4,'book','&#xe67a;','图书管理',2,3,'图书管理',1,0,'/houtai/book/manage'),(5,'role','&#xe677;','角色管理',2,1,'角色管理',1,0,'/houtai/role/manage'),(6,'menu','&#xe67a;','菜单管理',3,1,'菜单管理',1,0,'/houtai/menu/manage?pId=-1'),(7,'type','&#xe67a;','图书类型',1,3,'图书类型',1,0,'/houtai/book/type/manage');

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhayg4ib6v7h1wyeyxhq6xlddq` (`menu_id`),
  KEY `FK4n8tfik5mix35p0najxw5o8qi` (`role_id`),
  CONSTRAINT `FK4n8tfik5mix35p0najxw5o8qi` FOREIGN KEY (`role_id`) REFERENCES `t_a_role` (`id`),
  CONSTRAINT `FKhayg4ib6v7h1wyeyxhq6xlddq` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

/*Data for the table `t_role_menu` */

insert  into `t_role_menu`(`id`,`menu_id`,`role_id`) values (25,1,2),(26,2,2),(27,5,2),(28,6,2),(64,1,1),(65,2,1),(66,5,1),(67,6,1),(68,3,1),(69,7,1),(70,4,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
