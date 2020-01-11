-- MySQL dump 10.13  Distrib 8.0.18, for Linux (x86_64)
--
-- Host: localhost    Database: epub_learning
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `business_function`
--

DROP TABLE IF EXISTS `business_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_function` (
  `BUSINESS_FUNCTION` varchar(30) NOT NULL,
  `BUSINESS_FUNCTION_DESC` varchar(50) NOT NULL,
  PRIMARY KEY (`BUSINESS_FUNCTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_function`
--

LOCK TABLES `business_function` WRITE;
/*!40000 ALTER TABLE `business_function` DISABLE KEYS */;
INSERT INTO `business_function` VALUES ('ROLE_BE_VIEW_LEAD_PER','View Lead Performance Report only for Affiliate'),('ROLE_BF_ADMIN','Admin'),('ROLE_BF_AUDIT_REPORT','Audit trails and logs'),('ROLE_BF_CAN_CREATE_ROLES','Give rights for other Roles '),('ROLE_BF_CREATE_USER','Create user'),('ROLE_BF_EDIT_AFFILIATE_MAPPING','Affiliate Mapped with Lead Source'),('ROLE_BF_EDIT_USER','Edit user'),('ROLE_BF_LEAD_PERFORMANCE','View Lead Performance Report'),('ROLE_BF_LIST_USER','List user'),('ROLE_BF_OUTSIDE_ACCESS','provide Outside Access'),('ROLE_BF_PASSWORD_RESET','Reset Password and Failed attempts'),('ROLE_BF_SHOW_HOME','Home page'),('ROLE_BF_UPLOAD_LEADS','Upload leads'),('ROLE_BF_VENDOR_REPORT','vendor reports'),('ROLE_BF_VIEW_BAR_CHART','View Dashboard Bar chart'),('ROLE_BF_VIEW_DATEPICKER','View Date Picker as per Role'),('ROLE_BF_VIEW_LEADS_ALL','View all Leads'),('ROLE_BF_VIEW_LEADS_VENDOR','Vew Leads for a Vendor'),('ROLE_BF_VIEW_LEAD_LIST','View Lead List For Affiliate'),('ROLE_BF_VIEW_REPORTS','View Reports');
/*!40000 ALTER TABLE `business_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `can_create_roles`
--

DROP TABLE IF EXISTS `can_create_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `can_create_roles` (
  `ROLE_ID` varchar(30) DEFAULT NULL,
  `CAN_CREATE_ROLE` varchar(50) DEFAULT NULL,
  KEY `FK_can_create_roles_ROLE_ID` (`ROLE_ID`),
  KEY `FK_can_create_roles_CAN_CREATE_ROLE` (`CAN_CREATE_ROLE`),
  CONSTRAINT `FK_can_create_roles_CAN_CREATE_ROLE` FOREIGN KEY (`CAN_CREATE_ROLE`) REFERENCES `role` (`ROLE_ID`),
  CONSTRAINT `FK_can_create_roles_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `can_create_roles`
--

LOCK TABLES `can_create_roles` WRITE;
/*!40000 ALTER TABLE `can_create_roles` DISABLE KEYS */;
INSERT INTO `can_create_roles` VALUES ('ROLE_USER','ROLE_USER'),('ROLE_USER','ROLE_VENDOR'),('ROLE_TEST','ROLE_VENDOR'),('ROLE_SUPER','ROLE_SUPER'),('ROLE_SUPER','ROLE_USER'),('ROLE_SUPER','ROLE_VENDOR'),('ROLE_REPORT','ROLE_REPORT'),('ROLE_SUPER','ROLE_REPORT'),('ROLE_SUPER','ROLE_AFFILIATE');
/*!40000 ALTER TABLE `can_create_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `ROLE_ID` varchar(25) NOT NULL,
  `ROLE_NAME` varchar(25) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('Nikita','NIkita'),('ROLE_AFFILIATE','Affiliate Only'),('ROLE_REPORT','Report only'),('ROLE_SUPER','Super user'),('ROLE_TEST','Test role'),('ROLE_USER','User'),('ROLE_VENDOR','Vendor');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_business_function`
--

DROP TABLE IF EXISTS `role_business_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_business_function` (
  `ROLE_ID` varchar(25) DEFAULT NULL,
  `BUSINESS_FUNCTION_ID` varchar(30) DEFAULT NULL,
  KEY `FK_role_business_function_ROLE_ID` (`ROLE_ID`),
  KEY `FK_role_business_function_BUSINESS_FUNCTION_ID` (`BUSINESS_FUNCTION_ID`),
  CONSTRAINT `FK_role_business_function_BUSINESS_FUNCTION_ID` FOREIGN KEY (`BUSINESS_FUNCTION_ID`) REFERENCES `business_function` (`BUSINESS_FUNCTION`),
  CONSTRAINT `FK_role_business_function_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_business_function`
--

LOCK TABLES `role_business_function` WRITE;
/*!40000 ALTER TABLE `role_business_function` DISABLE KEYS */;
INSERT INTO `role_business_function` VALUES ('ROLE_SUPER','ROLE_BF_SHOW_HOME'),('ROLE_USER','ROLE_BF_SHOW_HOME'),('ROLE_VENDOR','ROLE_BF_SHOW_HOME'),('ROLE_SUPER','ROLE_BF_UPLOAD_LEADS'),('ROLE_VENDOR','ROLE_BF_VIEW_LEADS_VENDOR'),('ROLE_SUPER','ROLE_BF_VIEW_LEADS_ALL'),('ROLE_USER','ROLE_BF_VIEW_LEADS_ALL'),('ROLE_SUPER','ROLE_BF_VIEW_REPORTS'),('ROLE_USER','ROLE_BF_VIEW_REPORTS'),('ROLE_SUPER','ROLE_BF_CREATE_USER'),('ROLE_SUPER','ROLE_BF_EDIT_USER'),('ROLE_SUPER','ROLE_BF_LIST_USER'),('ROLE_USER','ROLE_BF_LIST_USER'),('ROLE_SUPER','ROLE_BF_PASSWORD_RESET'),('ROLE_SUPER','ROLE_BF_AUDIT_REPORT'),('ROLE_SUPER','ROLE_BF_CAN_CREATE_ROLES'),('ROLE_SUPER','ROLE_BF_ADMIN'),('ROLE_SUPER','ROLE_BF_OUTSIDE_ACCESS'),('ROLE_VENDOR','ROLE_BF_VIEW_REPORTS'),('Nikita','ROLE_BF_ADMIN'),('ROLE_REPORT','ROLE_BF_VIEW_REPORTS'),('ROLE_REPORT','ROLE_BF_SHOW_HOME'),('ROLE_AFFILIATE','ROLE_BF_VIEW_BAR_CHART'),('ROLE_AFFILIATE','ROLE_BF_VIEW_LEAD_LIST'),('ROLE_SUPER','ROLE_BF_EDIT_AFFILIATE_MAPPING'),('ROLE_AFFILIATE','ROLE_BE_VIEW_LEAD_PER'),('ROLE_AFFILIATE','ROLE_BF_SHOW_HOME'),('ROLE_AFFILIATE','ROLE_BF_VIEW_DATEPICKER'),('ROLE_USER','ROLE_BF_ADMIN');
/*!40000 ALTER TABLE `role_business_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(25) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `PASSWORD` tinyblob NOT NULL,
  `ACTIVE` tinyint(1) NOT NULL,
  `EXPIRED` tinyint(1) NOT NULL,
  `FAILED_ATTEMPTS` tinyint(3) NOT NULL,
  `EXPIRES_ON` date NOT NULL,
  `OUTSIDE_ACCESS` tinyint(4) NOT NULL,
  `LAST_LOGIN_DATE` datetime DEFAULT NULL,
  `CREATED_DATE` datetime NOT NULL,
  `CREATED_BY` int(11) NOT NULL,
  `LAST_MODIFIED_DATE` datetime NOT NULL,
  `LAST_MODIFIED_BY` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `unqUsername` (`USERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'super','Super User1',_binary '$2a$10$EaIPkTGK3Cxl5fXVKaa9UuWTrtmLo2uR6hptHRziJVSpH5dpRjeb6',1,0,0,'2020-02-02',1,'2019-12-01 12:28:21','2016-08-04 13:26:15',1,'2016-08-30 12:19:52',4),(5,'akil','Akil Mahimwala',_binary '$2a$10$k.vK/qCuLsLAgnGzbNn18.hO17x5hH0yqcSRtVqdpaP.owjlpl5/q',0,0,3,'2016-11-01',1,NULL,'2016-08-04 18:23:25',4,'2016-09-14 11:34:31',4),(14,'akil1','Akil Mahimwala',_binary '$2a$10$9/zoVAPr/lBbhlHBD3Okmu1b8/dVSXN5iSYBejTo1UJb3BHERo8ci',0,0,0,'2016-11-01',1,NULL,'2016-08-04 18:32:34',4,'2016-09-14 11:34:11',4),(15,'sumit','Sumit Mudiraj',_binary '$2a$10$HJ.9SjekCK/lk5A0B6fS8.w/FVO4W9R3MBM6Vd2XjvfsRlyVRyw6S',0,0,3,'2017-06-09',0,'2017-03-24 12:47:39','2016-08-30 12:17:03',4,'2017-07-16 09:20:30',47),(16,'s2scc','S2s',_binary '$2a$10$2i6FHZoQiSDeoiPqoCzOye6xVvrGtCw2lsCCy6Q51w2Wd1XkvdQPi',1,0,0,'2016-11-30',1,'2016-09-01 11:30:12','2016-08-30 15:39:49',4,'2016-08-30 15:39:49',4),(17,'sunil','Sunil Patil',_binary '$2a$10$/BiwIv3wx3Dl/IvCOHqQXuypVhgAHwiOuK4KcuEjfBiLdw09mfCKC',1,0,1,'2019-02-20',1,'2019-02-19 17:53:36','2016-08-30 19:01:54',4,'2017-07-29 14:36:50',47),(18,'maulesh','Maulesh Thakkar',_binary '$2a$10$bAqLya.eg3LEc3nQEkQ0I.yivOxZ9EgXVWiWAtJecsBLOJvCnmGNa',1,0,3,'2017-02-14',1,'2016-11-16 10:38:56','2016-08-30 19:02:53',4,'2016-08-31 11:29:14',4),(19,'imran','Imran Hassnain',_binary '$2a$10$rHEebzyGS5rkTcmwmcKczOUVYF8ngod8I1eSya7pJl7wm7hhFVWTK',1,0,1,'2016-12-12',1,'2016-09-13 12:09:23','2016-08-30 19:03:31',4,'2016-08-30 19:03:31',4),(20,'vinay','Vinay Thoke',_binary '$2a$10$Tb4tUgOBwVVvY3cueWQtdOxIktz1XsATbbcKkh9lpTvWu0C5jnnYy',1,0,0,'2020-02-17',1,'2019-11-30 15:24:23','2016-08-30 19:04:04',4,'2018-02-28 09:59:21',4),(21,'nabil','Nabil',_binary '$2a$10$SsKpCJBSEaiTO88NNnKd7OP0RMgoIULzRofgXwVVcytPG0vk5w2pm',1,0,0,'2016-12-01',1,'2016-09-12 15:52:18','2016-08-31 13:47:22',4,'2016-09-02 18:47:33',4),(22,'rahul_a','Rahul',_binary '$2a$10$kD20nac25xoav/woQC.mVuUi6rY/9vzHmqCYBLTBMbZMRipotWLm2',1,0,2,'2001-01-01',1,'2017-09-19 12:06:23','2016-09-01 13:08:12',4,'2016-09-01 13:15:46',4),(23,'s2smis','S2smis',_binary '$2a$10$KzQsPf4v0oqsa4JxuI4sFuTWuEZU/xbEJNdXa9e7kNoe8CsiiVyny',1,0,0,'2016-11-02',0,NULL,'2016-09-03 14:16:11',15,'2016-09-03 14:16:11',15),(24,'huzaifa','Huzaifa',_binary '$2a$10$uwYEHz79xO8gX1p1ji6YD.lXww/e3YCa21AWsZtXe80edke8g43p.',0,0,0,'2016-12-08',0,'2016-11-28 13:53:17','2016-09-03 15:25:24',15,'2017-04-28 14:42:53',30),(25,'earth','Earth',_binary '$2a$10$Pfh3xwV.NaycJa.I2zOvAeqRKkvCY0KtNHkwjE8tAqpSRxaI2UxPq',1,0,0,'2018-06-03',1,'2019-01-03 12:52:13','2016-09-09 12:43:33',15,'2018-06-15 13:09:17',33),(26,'monali','Monali',_binary '$2a$10$UZUOPa/FT8WvcQDNKNgoPeluuLheOoZfbrSt4pvRtk5DZwcZ1jqF2',1,0,3,'2017-02-13',1,'2016-11-15 10:19:02','2016-09-16 12:07:56',15,'2016-09-16 12:07:56',15),(27,'nikita','Nikita',_binary '$2a$10$ZxeYNfyfVp7x1AvB2D.jPe7IgxGJp5/yuBdjY4irVHgI1t/JLEtUS',0,0,0,'2019-03-12',0,'2019-01-14 11:10:45','2016-09-19 11:11:34',15,'2019-01-14 11:22:40',20),(28,'shruti','Shruti',_binary '$2a$10$zW2Ci2RW39.SNPUmSmmhK.oxH0GlGHRKsP4zGrPvXcmjyIKnKcoQy',1,0,0,'2016-11-27',1,'2016-09-28 14:18:12','2016-09-28 13:30:09',15,'2016-09-28 13:30:09',15),(29,'sunilpatil','Sunilpatil',_binary '$2a$10$VoZutZJwNi7Kf7s4fy9McOUtLGydnOXz7tX3MJ4hDE2ohchRGDLOq',1,0,0,'2019-12-18',1,'2019-09-19 18:55:34','2016-12-23 17:16:42',15,'2018-04-26 16:56:36',46),(30,'hiren','Hiren',_binary '$2a$10$MaHuOw2Jgkkw4a1qL3H2WejzypGdgXt91ijGNMtcN14cnytKl0h.G',0,0,0,'2017-09-11',0,'2017-07-03 16:05:19','2016-12-26 12:20:34',15,'2017-07-16 09:19:51',47),(31,'prasad','Prasad',_binary '$2a$10$BVkmhsSRWw2buiZjLd2pFOEytee7BlzDnpSL7jamwLiamYYW7Eu4m',1,0,3,'2017-07-02',1,'2017-04-03 18:53:51','2016-12-30 10:47:49',15,'2016-12-30 10:47:49',15),(32,'pradip','Pradip',_binary '$2a$10$NybX4bqHysEeNwSN1v9apuOzfBKN.ohmieOM0BA4XlMdvTFaXE7oy',1,0,0,'2017-03-10',1,'2017-01-31 13:34:35','2017-01-09 16:53:12',15,'2017-01-09 16:53:12',15),(33,'vijay','Vijay',_binary '$2a$10$4Q4TYvRyEz3dC7w5L1FDNeLf4bBRb.XhPz0m4BqZPzc7ciQu/UZ46',1,0,3,'2019-03-20',1,'2018-12-20 15:33:17','2017-01-13 12:22:27',15,'2018-03-16 11:59:27',46),(34,'vaibhav','Vaibhav',_binary '$2a$10$9aExslxL/b1CSJLwLdglKO7VmJW9.5RCgt3hteG2CLlht/ZrvlJGe',1,0,0,'2017-12-24',1,'2018-04-17 13:20:40','2017-01-25 14:55:04',15,'2017-01-25 14:55:04',15),(35,'samir','Samir',_binary '$2a$10$0KmjJOvzHUksVjD8MJMtU.DFpomD4fQR3IRk/L9e0/v6mJ/y9iCqK',0,0,1,'2019-09-14',0,'2019-08-28 09:49:00','2017-02-04 15:37:33',15,'2019-08-30 10:57:55',4),(38,'itsunilpatil','Sunil_patil',_binary '$2a$10$dNm9Srh8ibv9zwpiaw6RG.of69U1UDH4MNhyr/1xkCjozad23ZG3u',1,0,1,'2017-05-30',1,NULL,'2017-03-31 10:43:31',30,'2017-03-31 10:43:31',30),(42,'cc_nikita','Nikita Amin',_binary '$2a$10$AeVc2jpgbNLxLOSYJalHxea18bCpV26bGIbKOV5elrHCUw0LKjSVu',0,0,0,'2017-06-25',0,NULL,'2017-04-26 17:22:47',30,'2019-01-02 12:23:21',20),(43,'callcenter','Call Center',_binary '$2a$10$GTn4QX9Cxtdr.4fXITAMauX31CYTDiKK4wMUrJcrXOIGbHqOQb/X.',0,0,0,'2017-07-03',0,'2017-05-04 17:32:08','2017-05-04 17:31:42',29,'2017-05-04 17:33:22',29),(44,'sagar ubhare','Sagar Ubhare',_binary '$2a$10$6p52YZog78HLSO3rXaTQW.OwUlj9O.34WJ0Lr/WfVToL.0eLivTYy',1,0,3,'2017-08-14',1,NULL,'2017-06-15 15:29:57',30,'2017-08-17 12:27:37',45),(45,'durgesh nadar','Durgesh Nadar',_binary '$2a$10$/P4c0xh0q/OUmxxjosOBUeB5MRY44Lf7yBq7US1gZU.FnC98BWpYq',0,0,0,'2018-08-12',1,'2018-06-21 11:14:22','2017-06-15 15:31:07',30,'2018-10-23 14:51:37',33),(46,'sagar123','Sagar U',_binary '$2a$10$R0PxpyobKa4PoMXZlMBjtepTuwSYK9dNaK0KuujKkVdrwAd/M35Uq',1,0,3,'2018-09-06',1,'2018-06-28 19:11:50','2017-06-15 15:58:06',45,'2017-12-04 10:16:11',33),(47,'kanchan shah','Kanchan Shah',_binary '$2a$10$wAWxz4ac2vs.ZLPBNtRLe.t0M4yhzRhtLrScNLPj8MHBv4Jm0YgVS',0,0,3,'2018-04-10',0,'2018-01-10 08:42:17','2017-07-06 12:33:53',45,'2019-09-19 17:58:13',76),(48,'abanish','Abanish Seal',_binary '$2a$10$iCpdINAAOP4bopq0aDdqju13df0pInRmMy3VXLTszjEGyyOEYDB1a',0,0,0,'2018-01-31',0,'2017-11-21 15:03:05','2017-08-18 15:32:25',4,'2018-06-12 17:58:44',20),(49,'asklaika','Asklaika',_binary '$2a$10$ToG8l4pNFUiWXMGirzfh3.2TYCBF.u1N/0JJAiXrNyhH46994yCWW',1,0,0,'2018-01-05',1,'2017-11-06 18:06:39','2017-11-06 16:42:41',4,'2017-11-06 17:39:59',45),(50,'rohini','Rohini Daine',_binary '$2a$10$OEt4MpaOIQvxiggwO5UWzOKpZEsYPNm8lUzIKWXVItQF3TZizAC7e',1,0,0,'2018-05-27',1,'2018-03-28 15:40:37','2018-03-28 15:11:04',20,'2018-03-28 15:11:04',20),(51,'poornima','Poornima Verma',_binary '$2a$10$DpKnCNDVSHlrS7BkkFT7kuvzjLLX7tXcW0d1lpvmBDcrdvovV38MG',1,0,0,'2018-09-06',1,'2018-08-04 17:26:29','2018-03-28 15:12:20',20,'2018-03-28 15:12:20',20),(52,'nikhil','Nikhil Modak',_binary '$2a$10$yRBeNt6p5Nz/MyYAz.3R4uBa4lzmaLNSnkC9ZdDBhe.ihn70tea4m',1,0,0,'2020-02-16',1,'2019-12-01 19:17:39','2018-03-28 15:12:59',20,'2019-02-11 16:51:41',54),(53,'zarir','Zarir Damania',_binary '$2a$10$VvL0a/QNIJwE2neKXVRsbOvBM7uo14RHKtcc7Q1x2Lop23dYbyygq',1,0,0,'2020-02-03',1,'2019-12-01 18:22:47','2018-03-28 15:13:45',20,'2018-07-05 17:51:14',20),(54,'nitesh','Nitesh Goplani',_binary '$2a$10$rOFI0SHnoI0cvp9jWhyiAOQEwpgSPsYoMUHP4RbU8KJS7BpUMWq2a',1,0,0,'2019-08-29',1,'2019-05-31 13:00:49','2018-06-12 17:59:24',20,'2018-06-12 17:59:24',20),(55,'elvis','Elvis Roberts',_binary '$2a$10$f4/0Tlqwf1HOAVCSOyFizeqA07Y1XmmMwBYMYinCk/ZvLTem9hqle',1,0,0,'2020-01-14',1,'2019-10-16 13:21:56','2018-06-12 18:00:01',20,'2018-06-12 18:00:01',20),(56,'praful','Praful Sawant',_binary '$2a$10$k0RpvipYSsicb5nLhdSS4.10lULv46hr4uX2AO/Nc8FhpcCnNbN.W',1,0,0,'2019-02-13',1,'2019-04-04 14:39:30','2018-06-15 13:00:12',33,'2018-10-12 16:59:07',33),(57,'pankaj','Pankaj',_binary '$2a$10$Jlvz2PgcSmTC2j1YHjfavOk4ix0zq81c/Sl7UE2yHRka4mgGu7Bla',0,0,1,'2019-03-20',0,'2018-12-20 18:21:02','2018-06-19 10:23:16',33,'2019-09-19 17:59:16',76),(58,'amol','Amol Gupte',_binary '$2a$10$Bi21KxjCbnpld0AN8pFpy.OPM1IFHZGdmSpD5hAAAPVlzg8ASorNy',1,0,1,'2019-11-16',1,'2019-08-18 14:19:54','2018-07-20 14:32:51',20,'2019-04-25 14:44:31',35),(59,'arvind','Arvind',_binary '$2a$10$O5tz0yANjNw4WOBDMt6C9e31xLn/abN1ilVmnp/Sva.cQbcQ7Rca2',1,0,1,'2018-11-18',1,NULL,'2018-09-19 13:10:22',20,'2018-09-19 13:10:22',20),(60,'sakthi','Sakthi',_binary '$2a$10$ZYu3KPb.IYRWg2QgPTqmHu2lbGMt2iQibSUqPCBwg754jzPtq8Cvu',1,0,0,'2019-12-18',1,'2019-12-01 20:45:09','2018-10-15 15:56:59',33,'2019-01-03 11:58:06',4),(61,'vajra','Vajra',_binary '$2a$10$YaBV.xxLeuBMFhB00SDXpuO3M4gfzx1Llr.NWeUXvqy2s/96HetSq',1,0,3,'2019-06-23',1,'2019-03-26 10:41:06','2018-10-23 14:38:27',33,'2019-02-13 16:38:56',60),(62,'avinash','Avinash Shahu',_binary '$2a$10$dj8vAbfnVoFoYo.BuGAfCeclEVVVCmc/f1Ofx7dthSuusFIF20Lai',1,0,0,'2019-12-08',1,'2019-11-29 17:56:06','2018-12-13 17:10:04',4,'2018-12-13 17:10:04',4),(63,'shakti','Shakti',_binary '$2a$10$Z/e39nvdPe08QkmtcZhI/OAoxYs6yXOEstfgbApW5.HL/ShI7jyga',1,0,0,'2019-03-04',1,NULL,'2019-01-03 11:53:06',4,'2019-01-03 11:53:06',4),(64,'ranjan','Ranjan',_binary '$2a$10$y6OjPBrdT.rjwHjGE6DxPOehLzK4lms7Faxdc1./Q21U7wflwvsQC',1,0,0,'2020-02-07',1,'2019-11-29 14:25:49','2019-01-04 13:53:27',20,'2019-08-09 12:40:34',20),(66,'arvinds','Arvinds',_binary '$2a$10$B6.KavcbSTC2KGQa19hIreOOps5JfFKh3kjgxXW/gXa9ur3/zbtdS',1,0,0,'2019-12-11',1,'2019-12-01 14:36:19','2019-01-15 13:12:26',20,'2019-01-15 13:12:26',20),(67,'valueleaf','Valueleaf',_binary '$2a$10$IFFGiXGPGdSgmbJbHnQNfe6k6rBLBUX0q20a.Hutl/giu6F4TnGme',0,0,0,'2019-05-16',0,'2019-03-04 18:26:00','2019-02-04 16:08:23',4,'2019-09-19 17:56:42',76),(68,'clinicspots','Clinicspots',_binary '$2a$10$wUB01vEIa.fVRD3bgjsqRe4t7U78nFh7jRYQ4BhU6QYrSYjl1Oo7y',1,0,3,'2019-04-05',1,'2019-02-05 11:28:39','2019-02-04 18:06:11',60,'2019-02-04 18:06:11',60),(69,'mds tricho','Mds Tricho',_binary '$2a$10$ioNWpkYvaecGJALDs.62Fe860JCztcA6u.ETf52JhaS5sh7NhdHjC',0,0,0,'2019-07-17',0,'2019-06-26 17:27:04','2019-02-04 18:12:54',60,'2019-09-19 17:54:12',76),(70,'aqugen','Aqugen',_binary '$2a$10$n964bBKXjuP94Xnbd6LAUOgZW0O.S4G.GPm1CHAcQ.dmqYRRjvCuK',0,0,0,'2019-08-26',0,'2019-07-10 11:45:51','2019-02-04 18:24:51',60,'2019-09-19 17:53:32',76),(71,'intellectads','Intellectads',_binary '$2a$10$B6uIwBjSvdmrfHc8Y8E94.6Cmu.YPadC04rrwR56yFMTfzteqeO0W',0,0,1,'2019-08-25',0,'2019-05-30 12:11:53','2019-02-04 18:30:25',60,'2019-09-19 17:55:08',76),(72,'aqugenuser','Aqugenuser',_binary '$2a$10$mBJRSSVlrRcZGT796H3IQexW6j5g8v7.65/.pY8869ksIIYUoFX3S',0,0,0,'2019-04-06',0,'2019-02-11 13:51:44','2019-02-05 11:19:11',4,'2019-09-19 17:53:47',76),(73,'harsha vajra','Harsha Vajra',_binary '$2a$10$ZYu3KPb.IYRWg2QgPTqmHu2lbGMt2iQibSUqPCBwg754jzPtq8Cvu',1,0,2,'2019-04-14',1,'2019-02-15 16:40:37','2019-02-13 16:40:20',60,'2019-02-13 16:40:20',60),(74,'times internet','Times Internet',_binary '$2a$10$ScZJmZPy2k2dLOZsXdt.EO4IB1akz3fLRWwky6vT3FY3SQWxqVheO',0,0,0,'2019-04-19',0,'2019-02-25 13:36:46','2019-02-18 16:38:12',60,'2019-09-19 17:54:47',76),(75,'pnd bs','Pnd Bs',_binary '$2a$10$DjOkT42tQsYjiHhmlvDWXubIXOsMo9fi/Qcf2UE0kz3pUm9.qh1/2',0,0,0,'2019-08-01',0,'2019-06-11 10:20:25','2019-02-19 10:30:06',60,'2019-09-19 17:54:25',76),(76,'harshajai','Harshajai',_binary '$2a$10$ZYu3KPb.IYRWg2QgPTqmHu2lbGMt2iQibSUqPCBwg754jzPtq8Cvu',1,0,0,'2020-01-17',1,'2019-11-30 15:25:28','2019-02-20 17:24:21',60,'2019-02-20 17:24:21',60),(77,'icubeswire tricho','Icubeswire',_binary '$2a$10$GoZteOhuKbxo7PbDRb7sfODpc/JiQjGEBiDqcqR/IEeNk.LeAtMFG',0,0,0,'2019-07-17',0,'2019-04-30 15:57:39','2019-04-18 16:22:43',60,'2019-09-19 17:56:55',76),(78,' harsh',' Harsh',_binary '$2a$10$QsKeL7iBTdhbQ9We8U9m2eMyveccy3bVJB0BE.22QzvsZhI9G6qe.',0,0,0,'2019-06-21',0,NULL,'2019-04-22 11:41:11',60,'2019-09-19 17:56:11',76),(79,'fms-rf','Fms',_binary '$2a$10$B8gBLLvenuo5zNEjxULi.ORqYpFGmdi50C9aigddavnLF9ws/HqQO',0,0,0,'2019-07-16',0,'2019-06-01 12:14:35','2019-05-17 12:41:39',60,'2019-09-19 17:55:57',76),(80,'testuser','Test User',_binary '$2a$10$M4nuhHphN7LVs1XOJ/r14uRcVTkSJcru0Ved4x2W8sn9BYun33cxq',0,0,0,'2019-07-26',0,'2019-05-27 18:28:33','2019-05-27 18:20:26',4,'2019-09-19 17:57:10',76),(81,'lybrate','Lybrate',_binary '$2a$10$IMseD3euy8w/kHTDi7Ym0.QqZ30tt8LHdjsJvSyrlqSgij6MK7yl2',0,0,0,'2019-08-29',0,'2019-06-17 19:06:59','2019-05-31 15:01:01',60,'2019-09-19 17:56:27',76),(82,'pushpal','Pushpal ',_binary '$2a$10$hiPcjd.o5feYWv4s7kZa..zqRlyM4yX.7mLSSSHnD9fCNzI30xcL6',1,0,0,'2019-11-18',1,'2019-10-07 13:51:47','2019-06-21 15:05:23',52,'2019-06-21 15:05:23',52),(83,'neilesh','Neilesh',_binary '$2a$10$Cs.RIbvFD.Nu3HvmR1UW8epRQwesKirEYvnTuPPEtda0wDCzv3cya',1,0,0,'2019-08-20',1,'2019-06-24 11:26:17','2019-06-21 15:05:48',52,'2019-06-21 15:05:48',52),(84,'vendoruser','Vendor',_binary '$2a$10$crdt11oI/4bNPOe31crUtOWAgJdxqk9g6OYGB8Ik6PhtjYyeKV2Xq',1,0,0,'2019-10-11',0,'2019-08-12 16:13:12','2019-08-12 16:13:02',4,'2019-08-12 16:13:02',4),(85,'reportuser','Report',_binary '$2a$10$Mb/G1ANPhernSTSi92YD3u7nkIzr28ipTQT9jyUPqXwvjTtwREpcK',1,0,0,'2019-10-12',0,'2019-08-13 12:52:16','2019-08-13 12:52:05',4,'2019-08-13 12:52:05',4),(86,'kiran vanarse','Kiran Vanarse',_binary '$2a$10$//8bUUmgqDVSUCzrZRLs5O/k7tS433sbx6P/EPjCMbu1PP.jb0aYe',1,0,0,'2020-02-17',1,'2019-11-30 18:29:45','2019-08-20 17:42:03',4,'2019-08-20 17:42:03',4),(87,'pranayh','Pranay Haldankar',_binary '$2a$10$Vqlhq9/W64jOe.he/ijQf.SCjxdHq0qy5lf4771iTsnuSQdNSGJJ6',1,0,0,'2019-10-26',0,'2019-08-30 11:42:56','2019-08-27 10:56:25',4,'2019-08-27 10:56:25',4),(88,'testlms','Nsf',_binary '$2a$10$7SgJZ3d77l0Ek77ppJrunuY9nVTiRbdFGRLkhlAR/1.tLk6HP8fXe',1,0,0,'2019-10-29',0,'2019-08-30 11:05:18','2019-08-30 11:05:11',4,'2019-08-30 11:05:11',4),(89,'dijira','Dijira',_binary '$2a$10$S/jScxdFtDdHEUJA8P1aUuE5YgetIDJjeX1Q1JL5BgimLKpQ87JT6',1,0,0,'2019-11-02',0,'2019-09-26 10:58:56','2019-09-03 12:47:39',20,'2019-09-03 12:47:39',20),(90,'mohan','Mohan',_binary '$2a$10$LsyuESaSV/NYEsHE8coB2OvVmdcxg95inDAGcwwUhgXPzKAg5xgDy',1,0,0,'2020-02-21',1,'2019-12-01 20:42:29','2019-09-21 14:05:51',52,'2019-09-21 14:05:51',52),(91,'neeraj','Neeraj Vaishya',_binary '$2a$10$lvsxHk3UzZKbLhOrSwC81Oc3hkneJlziv8Q7vyg1Ykh7ZZ.7UODvS',1,0,0,'2020-02-23',0,'2019-11-30 11:34:58','2019-09-26 11:00:00',20,'2019-09-26 11:00:00',20),(93,'mds ht','Mds Tricho',_binary '$2a$10$HX1Qro1zKgQzrpXSPrLDGOiNV.LkU25clJzbjEj3lLCW1wEAGAlFC',1,0,0,'2019-12-10',1,NULL,'2019-10-11 15:50:40',76,'2019-10-11 15:50:40',76);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `USER_ID` int(10) unsigned NOT NULL,
  `ROLE_ID` varchar(25) NOT NULL,
  KEY `FK_user_role_USER_ID` (`USER_ID`),
  KEY `FK_user_role_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `FK_user_role_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ROLE_ID`),
  CONSTRAINT `FK_user_role_userId` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (4,'ROLE_SUPER'),(5,'ROLE_SUPER'),(14,'ROLE_SUPER'),(15,'ROLE_SUPER'),(16,'ROLE_VENDOR'),(17,'ROLE_VENDOR'),(18,'ROLE_USER'),(19,'ROLE_VENDOR'),(20,'ROLE_SUPER'),(21,'ROLE_USER'),(22,'ROLE_USER'),(23,'ROLE_VENDOR'),(24,'ROLE_USER'),(25,'ROLE_REPORT'),(26,'ROLE_USER'),(27,'ROLE_SUPER'),(28,'ROLE_USER'),(29,'ROLE_SUPER'),(30,'ROLE_SUPER'),(31,'ROLE_USER'),(32,'ROLE_USER'),(33,'ROLE_SUPER'),(34,'ROLE_USER'),(35,'ROLE_SUPER'),(38,'ROLE_SUPER'),(42,'ROLE_SUPER'),(43,'ROLE_USER'),(44,'ROLE_SUPER'),(45,'ROLE_REPORT'),(46,'ROLE_SUPER'),(47,'ROLE_SUPER'),(48,'ROLE_SUPER'),(49,'ROLE_REPORT'),(50,'ROLE_USER'),(51,'ROLE_SUPER'),(52,'ROLE_SUPER'),(53,'ROLE_SUPER'),(54,'ROLE_SUPER'),(55,'ROLE_SUPER'),(56,'ROLE_USER'),(57,'ROLE_SUPER'),(58,'ROLE_SUPER'),(59,'ROLE_USER'),(60,'ROLE_SUPER'),(61,'ROLE_SUPER'),(62,'ROLE_SUPER'),(63,'ROLE_SUPER'),(64,'ROLE_SUPER'),(66,'ROLE_REPORT'),(67,'ROLE_AFFILIATE'),(68,'ROLE_AFFILIATE'),(69,'ROLE_AFFILIATE'),(70,'ROLE_AFFILIATE'),(71,'ROLE_AFFILIATE'),(72,'ROLE_AFFILIATE'),(73,'ROLE_REPORT'),(74,'ROLE_AFFILIATE'),(75,'ROLE_AFFILIATE'),(76,'ROLE_SUPER'),(77,'ROLE_AFFILIATE'),(78,'ROLE_AFFILIATE'),(79,'ROLE_AFFILIATE'),(80,'ROLE_AFFILIATE'),(81,'ROLE_AFFILIATE'),(82,'ROLE_SUPER'),(83,'ROLE_SUPER'),(84,'ROLE_VENDOR'),(85,'ROLE_REPORT'),(86,'ROLE_USER'),(87,'ROLE_USER'),(88,'ROLE_USER'),(89,'ROLE_USER'),(90,'ROLE_SUPER'),(91,'ROLE_USER'),(93,'ROLE_AFFILIATE');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-09 16:05:57
