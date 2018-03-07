-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: itpdb
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `active_users`
--

DROP TABLE IF EXISTS `active_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `active_users` (
  `cookieId` varchar(18) NOT NULL,
  `userId` int(11) NOT NULL,
  `ip` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `active_users`
--

LOCK TABLES `active_users` WRITE;
/*!40000 ALTER TABLE `active_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `active_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `author` varchar(200) DEFAULT NULL,
  `status` varchar(10) DEFAULT 'none',
  `amount` int(11) DEFAULT '5',
  `description` varchar(1000) DEFAULT NULL,
  `teg` varchar(200) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `publisher` varchar(150) DEFAULT NULL,
  `edition` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `documents_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (2,'Book One','Author','bestseller',1,'Short description','#book','book',2018,'Drofa','300th'),(4,'Reference book','Book','reference',10,'some notes','Bookkk','book',2000,'Moscow','3th'),(5,'Second book','alia','none',0,'Note',NULL,'book',2018,'alia','5th'),(6,'AV','AV','none',0,NULL,NULL,'av',NULL,NULL,NULL),(7,'AV2','Audio','none',0,NULL,NULL,'av',NULL,NULL,NULL),(8,'Book2','www','none',4,'www',NULL,'book',111,'www','www'),(9,'Introduction to Algorithms','Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest and Clifford Stein','status',3,'Publisher: MIT Press.Year: 2009.Edition: Third edition','teg','book',NULL,NULL,NULL),(10,'Design Patterns: Elements of Reusable Object-Oriented Software','Erich Gamma, Ralph Johnson, John Vlissides, Richard Helm','bestseller',2,'Publisher: Addison-Wesley Professional. Year: 2003.Edition: First edition','teg','book',NULL,NULL,NULL),(11,'The Mythical Man-month','Brooks,Jr., Frederick P','reference',0,'Publisher: Addison-Wesley Longman Publishing Co., Inc.Year: 1995.Edition: Second edition','teg','book',NULL,NULL,NULL),(12,' Null References: The Billion Dollar Mistake','Tony Hoare','status',1,'','teg','video',NULL,NULL,NULL),(13,' Information Entropy','Claude Shannon','status',1,'','teg','video',NULL,NULL,NULL);
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `itemId` int(11) DEFAULT NULL,
  `startTime` mediumtext NOT NULL,
  `finishTime` mediumtext NOT NULL,
  `status` varchar(15) DEFAULT 'open',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (23,18,2,'1520191067396','1521400667396','closed'),(24,20,2,'1520191166347','1521400766347','closed'),(25,18,2,'1520419925943','1521629525943','closed'),(26,18,2,'1520420090340','1521629690340','closed'),(27,18,2,'1520420099870','1521629699870','closed'),(28,18,2,'1520420118575','1521629718575','closed'),(29,20,2,'1520423211731','1521632811731','closed'),(30,18,2,'1520423391402','1521632991402','closed'),(31,18,2,'1520428731651','1521638331651','finished'),(32,18,2,'1520429917500','1521639517500','finished'),(33,18,8,'1520430504484','1523886504484','open');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(150) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `cookieId` varchar(20) DEFAULT '0',
  `status` varchar(10) DEFAULT 'disabled',
  `fine` int(11) DEFAULT '0',
  `address` varchar(150) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (18,'sk@innopolis.ru','$2a$10$fbyzdCGyjlTdkJP11fi6H.5KIKuQmOka3qAwfwEYILl8SAv3NriRu','Sem','Kiss','49065118137528','admin',0,'VRN holz','8888'),(20,'kk@innopolis.ru','$2a$10$iyD/zhhebO5o7MKxPsFs3..BqxSUEC2HYpxd..EtEaeBCEwQiKK4K','Kekocich','lol','23855220582232','disabled',0,'Innopolis','88005553535'),(21,'new','$2a$10$oA76GucANkgAGfBpX1oUs.Vw5As3hv2sy1eYviKkWez6.f41vaRam','new','new','0','admin',0,NULL,NULL),(22,'user@innopolis.ru','$2a$10$III35myPMCXg8XJ2OrA/Uu77N.M9E9PQWR9DkB/SBKvyLbIK7/3A2','New','User','76340322615993','disabled',0,'Hometown','2746568'),(23,'sergey@innopolis.ru','$2a$10$BJWPCSqvssBXMLGrPm783OotNcmmHliFB8BmE4IJmmP89OyQKAP6u','Sergey','Afonso','0','faculty',0,NULL,NULL),(25,'elvira@innopolis.ru','$2a$10$XUZsqqge0ByV9Dw.Xbs3XOKW.wZx/nCmpkacFOFyTumfUz1freedO','Elvira','Pindosskaya','0','student',0,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-07 17:06:13
