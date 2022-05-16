-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: b2c
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `emailcus` varchar(255) DEFAULT NULL,
  `namecus` varchar(255) DEFAULT NULL,
  `rate` int DEFAULT NULL,
  `reviewtext` varchar(255) DEFAULT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (10,'hieumm@com','hieu',5,'tốt lắm',1),(20,'hieumm@com','hieu',3,'ti vi hoiw xaasu',5),(19,'fa@a.as','fa',6,'được vát gọt tinh xảo và dây đeo cao su co dãn, êm tay',1),(21,'hieumm@com','check1',4,'Thiết kế sang trọng, hiện đại, nâng tầm giá trị ',1),(22,'hieumm@com','hiếu',0,'Mẫu Apple Watch S6 là phiên bản nâng cấp so với dòng đồng hồ thông minh Apple S5. Đợt update lần này hứa hẹn sẽ mang đến những trải nghiệm tốt và thú vị hơn ',1),(23,'hieumm@com','mai hieu',4,'Chiếc Apple Watch S6 40mm viền nhôm dây cao su này vẫn giữ trọn vẹn nét tinh tế và sắc sảo trong thiết kế giống Apple Watch S5,',4),(24,'fa@a.as','1',4,'ô',1),(25,'fa@a.as','hieu',6,'ok',4),(26,'hieumm@com','đây là kiểm tra',0,'123',4),(27,'hieumm@com','check2',5,'123123',4);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 16:58:36
