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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `branch` varchar(255) DEFAULT NULL,
  `detail` text,
  `img1` varchar(255) DEFAULT NULL,
  `img2` varchar(255) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `videosrc` varchar(255) DEFAULT NULL,
  `yearofmanufacture` varchar(255) DEFAULT NULL,
  `cart_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtpn0u2u177t0ufycirtv9ag7x` (`cart_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Samsung','Đây là 1 loại đá hóa học, khi muốn làm lạnh thì cho hộp đá khô vào ngăn đá tủ lạnh (đá này có thể tái sử dụng), sau đó cho vào bình chứa nước của quạt, đá khô sẽ có công dụng làm lạnh nước giúp hơi nước tỏa ra được mát mẻ hơn.','5_2022/165173132901-1020x570-1020x570.jpg','5_2022/1651731329qled-4k-samsung-qa50q80a-300721-1239060.png',12000000,'Smart Tivi QLED 4K 50 inch Samsung QA50Q80A','https://youtu.be/1WEyqc_KDVU','2021',1),(4,'Samsung','Bộ lọc than hoạt tính Deodorizer trên tủ lạnh Samsung Inverter 208 lít RT20HAR8DBU/SV giúp loại bỏ hoàn toàn những mùi hôi khó chịu, các nấm mốc và vi khuẩn phát sinh bên trong tủ, để không khí trong tủ lạnh luôn tươi mát và thực phẩm trọn vị tươi ngon.','5_2022/1651731436samsung-rt20har8dbu-sv-080821-0639041.jpg','5_2022/1651731436samsung-rt20har8dbu-sv-140821-0959250.jpg',1244562423,'Tủ lạnh Samsung Inverter 208 lít RT20HAR8DBU/SV ','https://www.youtube.com/embed/LUH7njvhydE','1999',2),(5,'Casper','Smart Tivi Casper 32 inch 32HX6200 thiết kế lịch lãm với gam màu đen sang trọng, màn hình tràn viền 3 cạnh, cho tầm nhìn của bạn \"khóa chặt\" vào nội dung đang được trình chiếu, hạn chế xao nhãng khi xem tivi. Chân đế chữ V úp ngược giữ tivi đứng ổn định trên mặt bàn, kệ.  Kích cỡ tivi 32 inch nhỏ gọn, bày trí phù hợp cho phòng ngủ, phòng khách, văn phòng nhỏ theo kiểu để bàn hoặc treo tường tùy theo thiết kế nội thất của ngôi nhà, công ty.','5_2022/1651731704casper-32hx6200-070621-0314071.jpg','5_2022/1651731704casper-32hx6200-010721-1016550.jpg',34902423,'Smart Tivi Casper 32 inch 32HX6200','https://youtu.be/1WEyqc_KDVU','2021',1),(7,'Samsung','Tủ lạnh Samsung Inverter 655 lít RS62R5001M9/SV có thiết kế kiểu tủ lạnh side by side đẳng cấp, đi cùng gam màu bạc sang trọng, thời thượng, tủ lạnh sẽ là điểm nhấn nổi bật, mang lại cho không gian nội thất của gia đình một vẻ đẹp hiện đại.','5_2022/16517315341-1020x570.jpg','5_2022/16517315342-1020x570.jpg',11123121,'Tủ lạnh Samsung Inverter 655 lít RS62R5001M9/SV ','https://youtu.be/CpSJ4z_gXy0','2021',1),(8,'Toshiba','Siêu sóng Greatwave được tích hợp 3 công nghệ (Flush waves, Real Inverter và Color care) nên chiếc máy giặt này có thể tạo ra luồng sóng nước mạnh mẽ, giúp đánh bay vết bẩn một cách nhanh chóng cũng như mang lại hiệu quả giặt sạch tối ưu.','5_2022/1651731854Frame(52)-min-1020x570.jpg','5_2022/1651731854vi-vn-toshiba-tw-bk105s3v-sk-02.jpg',52739000,'Máy giặt Toshiba Inverter 9.5 Kg TW-BK105S3V(SK) ','ko link','2021',4),(9,'Samsung','Bảng điều khiển thông minh AI Control này sẽ tự động ghi nhớ các chế độ giặt giũ mà bạn thường sử dụng, từ đó sẽ tiến hành đề xuất chế độ giặt phù hợp với loại đồ theo thói quen sử dụng. Màn hình hiển thị quy trình giặt dễ hiểu, hướng dẫn thông minh bất kỳ ai trong gia đình đều có thể sử dụng dễ dàng.','5_2022/165173200201-1020x570.jpg','5_2022/1651732002vi-vn-samsung-addwash-ww85t554daw-sv-2.jpg',12590000,'Máy giặt Samsung Addwash Inverter 8.5kg WW85T554DAW/SV','ko link','2021',4),(10,'2','2','6_2022/1651825243samsung-rt20har8dbu-sv-140821-0959250.jpg','6_2022/1651825243samsung-rt20har8dbu-sv-140821-0959250.jpg',2,'1','2','2',2),(11,'123','123','6_2022/1651825358qled-4k-samsung-qa50q80a-300721-1239060.png','6_2022/165182535801-1020x570-1020x570.jpg',123,'123','No','123',5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 16:58:35
