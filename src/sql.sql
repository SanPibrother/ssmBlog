/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `blog`;

/*Table structure for table `article_info` */

DROP TABLE IF EXISTS `article_info`;

CREATE TABLE `article_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `content` mediumtext NOT NULL,
  `content_text` varchar(200) NOT NULL,
  `cover` varchar(100) NOT NULL,
  `view_count` int(11) NOT NULL,
  `update_time` date NOT NULL,
  `status` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `article_info` */

insert  into `article_info`(`id`,`title`,`content`,`content_text`,`cover`,`view_count`,`update_time`,`status`,`type_id`) values (9,'java ','<p></p><p>为<br></p><p></p>','为','http://localhost:8080/upload/2018/08/03/ab6bfb89-19cd-41ed-9194-791a8bd30483.jpg',3,'2018-08-03',1,3),(10,'问问','<p></p><p>为</p><p></p>','为','http://localhost:8080/upload/2018/08/01/12ad60be-4b3c-4b77-88fb-00e98f46a9c5.png',1,'2018-08-01',1,3),(11,'而','<p></p><p></p><p>为<br></p><p></p><p></p>','为','http://localhost:8080/upload/2018/08/01/ad5eaeb5-372c-4706-b503-33afe0a82fdb.png',1,'2018-08-01',0,3),(12,'1323','<p></p><p></p><p><br></p><p></p><p></p>','12314','http://localhost:8080/upload/2018/08/01/9e97af4b-fae1-41ee-9cd2-03770a1d606c.png',1,'2018-08-01',1,3),(13,'钱揾钱无','<p>的温情二多群二</p>','的温情二多群二','http://localhost:8080/upload/2018/08/01/d44d5da0-5da7-4957-b083-fb7e52e9606b.png',1,'2018-08-01',1,1),(14,'请问','<p><br></p><p><br></p><p>为而去解扰皮卡丘乳胶漆</p>','为而去解扰皮卡丘乳胶漆','http://localhost:8080/upload/2018/08/01/7e254b96-fdce-4f89-8356-52f199590ef4.png',1,'2018-08-01',1,3),(15,'请问','<p>wqee</p>','wqee','http://localhost:8080/upload/2018/08/02/0a6d1f6f-3b09-44ee-9734-40f5eb1685b3.png',1,'2018-08-02',0,7),(16,'请问','<p>wqee</p>','wqee','http://localhost:8080/upload/2018/08/02/0a6d1f6f-3b09-44ee-9734-40f5eb1685b3.png',1,'2018-08-02',0,7),(18,'1231','<p>2131</p>','2131','http://localhost:8080/upload/2018/08/02/9b1e357b-0d5e-4527-bc70-86d53e8d0513.png',3,'2018-08-02',1,3),(19,'23131','<p>123131</p>','123131','http://localhost:8080/upload/2018/08/03/31f396d8-db4a-4332-bf6b-025dad93a3a0.jpg',3,'2018-08-03',1,3),(20,'wode','<p>qewqeqeqeqwe</p>','qewqeqeqeqwe','http://localhost:8080/upload/2018/08/03/0ad2244c-4f8a-443e-a8ca-9806a6c959ca.jpg',21,'2018-08-03',1,3),(21,'adadada','<p>123131</p>','123131','http://localhost:8080/upload/2018/08/03/8ca7fbae-9112-48a1-af4f-3698c12c6d95.jpg',1,'2018-08-03',1,8),(22,'asfafafas','<p>123131</p>','123131','http://localhost:8080/upload/2018/08/03/637c795e-acda-4830-a52c-6fb9b4643f76.jpg',1,'2018-08-03',1,3),(23,'EQWE','<p>QWEQE</p>','QWEQE','http://localhost:8080/upload/2018/08/03/db99049e-a00a-4d4e-81f7-90670d676d9b.jpg',3,'2018-08-03',1,8),(24,'YUTYU','<p>23132131</p>','23132131','http://localhost:8080/upload/2018/08/03/b325db9c-953c-4568-a8ca-277973ea0931.jpg',1,'2018-08-03',1,3),(25,'wedeqwe','<p>wdewqdqawd<br></p>','wdewqdqawd','http://localhost:8080/upload/2018/08/03/a740b98e-7185-4868-8f5d-28af30275e9d.jpg',1,'2018-08-03',1,3);

/*Table structure for table `type_info` */

DROP TABLE IF EXISTS `type_info`;

CREATE TABLE `type_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sort` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `type_info` */

insert  into `type_info`(`id`,`name`,`sort`) values (1,'javaee',2),(3,'javaSe',1),(7,'javaScrpit',4),(8,'dada',3);

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(50) NOT NULL,
  `pass_word` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user_info` */

insert  into `user_info`(`id`,`login_name`,`pass_word`) values (1,'admin','123456');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
