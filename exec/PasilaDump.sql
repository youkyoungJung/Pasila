-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: i10a402.p.ssafy.io    Database: pasila
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `name` varchar(10) DEFAULT NULL,
  `image_url` varchar(2083) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'뷰티','beauty_image_url'),(2,'음식','food_image_url'),(3,'패션','fashion_image_url'),(4,'라이프','life_image_url'),(5,'여행/체험','travel_experience_image_url'),(6,'테크','tech_image_url'),(7,'유아','kids_image_url'),(8,'레저','activity_image_url'),(9,'티켓','ticket_image_url');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chatbot`
--

DROP TABLE IF EXISTS `chatbot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatbot` (
  `id` bigint NOT NULL,
  `answer` varchar(300) DEFAULT NULL,
  `question` varchar(300) DEFAULT NULL,
  `live_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKti0u8d74mcvpg6kb8sc2xomnf` (`live_id`),
  CONSTRAINT `FKti0u8d74mcvpg6kb8sc2xomnf` FOREIGN KEY (`live_id`) REFERENCES `live` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatbot`
--

LOCK TABLES `chatbot` WRITE;
/*!40000 ALTER TABLE `chatbot` DISABLE KEYS */;
INSERT INTO `chatbot` VALUES (61,'5가지 컬러가 있습니다.','컬러는 몇가지가 있나요?','ZE4CpCVAMd'),(62,'오래 끼고 있어도 귀가 편합니다!','오래 끼고 있어도 편한가요?','BdKehKDVj3'),(63,'포도맛입니다.','무슨맛인가요?','AoZ1Z9Zzsg'),(64,'구성은 단 하나 입니다.','구성은 어떻게 되나요','WfyozPBT0j'),(65,'항상 다르지만 약 20개 들어있습니다.','몇개 들어있나요','WfyozPBT0j'),(66,'매운맛과 중간맛으로 구성됩니다.','구성이 어떻게 되나요','ZE4SPrsiMd'),(67,'','','20Us30xwYM'),(68,'','','5mX0mgmyQC'),(69,'','','MhOIjmxGno'),(70,'','','tQiYnYxCyv'),(71,'','','QxbTwBuwAc'),(72,'','','mg2Fo4gaxO'),(73,'','','qjaoSffx3Y'),(74,'엄청나게 촉촉합니다.','많이 촉촉한가요?','3wtloRgaWT'),(75,'한장에 만원인데, 9500원으로 판매합니다.','얼마에요?','3wtloRgaWT'),(76,'','','qjaoPqyL3Y'),(77,'M, L, XL 준비되어있습니다.','사이즈는 어떻게 있나요','8inOYlHbR7');
/*!40000 ALTER TABLE `chatbot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chatbot_seq`
--

DROP TABLE IF EXISTS `chatbot_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatbot_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatbot_seq`
--

LOCK TABLES `chatbot_seq` WRITE;
/*!40000 ALTER TABLE `chatbot_seq` DISABLE KEYS */;
INSERT INTO `chatbot_seq` VALUES (78);
/*!40000 ALTER TABLE `chatbot_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live`
--

DROP TABLE IF EXISTS `live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live` (
  `is_active` tinyint(1) DEFAULT '1',
  `is_on` tinyint(1) DEFAULT '0',
  `like_cnt` int DEFAULT '0',
  `created_at` datetime(6) DEFAULT NULL,
  `live_off_at` datetime(6) DEFAULT NULL,
  `live_on_at` datetime(6) DEFAULT NULL,
  `live_scheduled_at` datetime(6) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `full_video_url` varchar(2083) DEFAULT NULL,
  `id` varchar(10) NOT NULL,
  `product_id` varchar(12) DEFAULT NULL,
  `script` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_88cufynaw0v7533g8q7bd0uuu` (`product_id`),
  KEY `FKl6he3gaf8twhlabx224oqfnp5` (`member_id`),
  CONSTRAINT `FK4btdqn37hgcjretuullv4dpjg` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKl6he3gaf8twhlabx224oqfnp5` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live`
--

LOCK TABLES `live` WRITE;
/*!40000 ALTER TABLE `live` DISABLE KEYS */;
INSERT INTO `live` VALUES (1,0,0,'2024-02-13 13:46:33.000000','2024-02-15 23:22:31.061401','2024-02-15 23:21:22.129784','2024-02-15 13:46:33.000000',1,'리쥬리프X채우 발렌타인 특집 LIVE!','https://i10a402.p.ssafy.io/openvidu/recordings/11ABCR5~1/11ABCR5~1.mp4','11ABCR5','11FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000',NULL,NULL,'2024-02-15 13:07:22.000000',2,'프리메라 24년 첫 브랜드데이 LIVE','https://i10a402.p.ssafy.io/download/live/12ABCR5.mp4','12ABCR5','12FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000','2024-02-16 01:01:56.217665','2024-02-16 01:00:18.668060','2024-02-15 13:07:22.000000',1,'[이니스프리x브랜드데이] 빅세일 특집 LIVE','https://i10a402.p.ssafy.io/openvidu/recordings/13ABCR5/13ABCR5.mp4','13ABCR5','13FD88R5Y5XM','스크립트'),(0,1,0,'2024-02-16 01:57:42.181331',NULL,'2024-02-16 02:01:12.008316','2024-02-16 02:57:00.000000',31,'[지우X미우미우] FW컬렉션대개봉!',NULL,'20Us30xwYM','h7keT7CiBvQ8',''),(1,0,0,'2024-02-13 13:46:33.000000',NULL,NULL,'2024-02-15 13:07:22.000000',3,'기장끝집 선물세트 NEW 출시!? 전복죽_전복미역국','https://i10a402.p.ssafy.io/download/live/21ABCR5.mp4','21ABCR5','21FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000','2024-02-16 10:01:23.330314','2024-02-16 10:01:08.123221','2024-02-15 13:07:22.000000',8,'아삭하고 달콤한 성주 꿀 참외 무료배송','https://i10a402.p.ssafy.io/openvidu/recordings/22ABCR5/22ABCR5.mp4','22ABCR5','22FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000',NULL,NULL,'2024-02-15 13:07:22.000000',3,'콩고미의 맛있는 현미&현미찹, 야채볶음밥 누룽지?','https://i10a402.p.ssafy.io/download/live/23ABCR5.mp4','23ABCR5','23FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000','2024-02-16 09:40:06.513230','2024-02-16 09:39:32.437129','2024-02-15 13:07:22.000000',6,'30%할인! 블랙진?1+1 봄신상 데일리룩','https://i10a402.p.ssafy.io/openvidu/recordings/31ABCR5/31ABCR5.mp4','31ABCR5','31FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000','2024-02-16 00:36:24.268151','2024-02-16 00:35:50.915534','2024-02-15 13:07:22.000000',1,'아묻따 마인봄신상❤️ 라방특가 할인❤️','https://i10a402.p.ssafy.io/download/live/32ABCR5.mp4','32ABCR5','32FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000',NULL,NULL,'2024-02-15 13:07:22.000000',2,'FW클리어런스 행사 ? 마지막 SALE ?','https://i10a402.p.ssafy.io/download/live/33ABCR5.mp4','33ABCR5','33FD88R5Y5XM','스크립트'),(1,0,1,'2024-02-16 08:50:58.987555','2024-02-16 08:54:04.970343','2024-02-16 08:51:56.574324','2024-02-17 16:10:00.000000',1,'아비브 거미 시트 마스크! 안사면 후회합니다!','https://i10a402.p.ssafy.io/openvidu/recordings/3wtloRgaWT/3wtloRgaWT.mp4','3wtloRgaWT','AoZd7wwEs1ig','1. 오프닝 / 인사\n- 안녕! 김로제야. 오늘은 아비브 거미 시트 마스크 소개할거야. 준비됐어?\n\n2. 상품소개/ 라이브 방송 혜택 안내\n- 아비브 거미 시트 마스크를 팔건데, 이번 라이브 특별가로 5%나 할인된 가격으로 판매해.\n- 1장 사면 1장을 더주는 특별 이벤트도 있으니 많이많이 구매해봐!\n\n3. 상품 구성 소개 / 포장 상태 설명 / 크기 비교\n- 아비브 거미 시트 마스크는 10장이 한 세트로 구성되어 있어.\n- 포장은 깔끔하게 되어 있고, 크기도 휴대하기에 편리해.\n\n4. 제품의 특징 강조\n- 아비브 거미 시트 마스크는 피부에 꼭 맞는 신축성 있는 재질로 만들어져 있어.\n- 거미줄처럼 얇고 가벼워서 피부에 부담 없이 착용할 수 있어.\n- 피부에 촉촉함을 더해주는 효과도 있어.\n\n5. 제품을 활용할 수 있는 여러 방법 / 사용하는 용도\n- 아비브 거미 시트 마스크는 주로 얼굴에 사용하지만, 목이나 손 등 다른 부위에도 사용할 수 있어.\n- 피부 트러블 개선이나 보습, 탄력 등 다양한 효과를 기대할 수 있어.\n\n6. 제품 사용 방법 안내\n- 사용 방법은 매우 간단해. 마스크를 얼굴에 부착한 후 10-15분 정도 지난 후에 제거하면 돼.\n- 남은 액체는 가볍게 토닝 또는 마사지하여 흡수시켜주면 좋아.\n\n7. 타사와의 차별점 설명\n- 아비브 거미 시트 마스크는 다른 마스크와 비교해도 피부에 부담 없이 착용할 수 있는 신축성 있는 재질로 만들어져 있어.\n- 또한, 피부에 촉촉함을 더해주는 효과도 있어서 피부 관리에 더욱 효과적이야.\n\n8. 다시 한번 제품 구성과 가격 강조\n- 아비브 거미 시트 마스크는 10장이 한 세트로 구성되어 있어.\n- 이번 라이브 특별가로 5% 할인된 가격으로 판매해.\n- 또한, 1장 사면 1장을 더해드리는 특별 이벤트도 진행 중이니 많은 구매 부탁해!'),(1,0,0,'2024-02-13 13:46:33.000000',NULL,NULL,'2024-02-15 13:07:22.000000',7,'❤️[10%할인+사은품]❤️네르하 이케바나 화병 라이브','https://i10a402.p.ssafy.io/download/live/41ABCR5.mp4','41ABCR5','41FD88R5Y5XM','스크립트'),(1,0,0,'2024-02-13 13:46:33.000000',NULL,NULL,'2024-02-15 13:07:22.000000',7,'예쁜 소품들 다 모였어요✨감성 인테리어 소품샵? ','https://i10a402.p.ssafy.io/download/live/42ABCR5.mp4','42ABCR5','42FD88R5Y5XM','스크립트'),(0,0,0,'2024-02-16 02:03:34.659286','2024-02-16 02:06:57.425932','2024-02-16 02:05:41.891203','2024-02-16 02:03:00.000000',31,'못난이 약과 팝니다!! 겉바속쫀!!','https://i10a402.p.ssafy.io/openvidu/recordings/5mX0mgmyQC/5mX0mgmyQC.mp4','5mX0mgmyQC','uWGAWfne2YK1',''),(1,0,0,'2024-02-16 10:16:57.083590','2024-02-16 10:18:34.304728','2024-02-16 10:17:23.909584','2024-02-17 10:16:00.000000',3,'맨투맨 팔아요~!!!','https://i10a402.p.ssafy.io/openvidu/recordings/8inOYlHbR7/8inOYlHbR7.mp4','8inOYlHbR7','Iec9MRx9vBlc','1. 오프닝 / 인사\n- 안녕하냥~ 여러분을 환영합니당~ 난 유리나! 오늘은 맨투맨 소개할거얍!\n\n2. 상품소개 / 라이브 방송 혜택 안내\n- 오늘은 회색맨투맨과 파란맨투맨을 소개할 거양! 이 맨투맨은 남녀공용으로 사용할 수 있어! 또, 라이브 방송 시간 동안 특별한 혜택을 제공할 예정이니 꼭 놓치지 마셈!\n\n3. 상품 구성 소개 / 포장 상태 설명 / 크기 비교\n- 이 맨투맨은 베이직한 디자인으로 제작되었고, 회색과 파란색으로 구성되어 있어! 포장 상태는 깔끔하게 제공되며, 크기는 S, M, L 사이즈로 다양하게 준비되어 있음!\n\n4. 제품의 특징 강조\n- 이 맨투맨은 면폴리 혼방 소재로 제작되어 가볍고 촉감이 우수해! 또한, 후로피 기법의 윌슨 레터링으로 디자인되어 있어서 포인트를 줌!\n\n5. 제품을 활용할 수 있는 여러 방법 / 사용하는 용도\n- 이 맨투맨은 다양한 스타일에 맞게 활용할 수 있어! 캐주얼한 룩에도 잘 어울리며, 스포츠 활동이나 야외 활동에도 편안하게 착용할 수 있음!\n\n6. 제품 사용 방법 안내\n- 맨투맨은 세탁기에서 손쉽게 세탁할 수 있음! 세탁 시에는 중성세제를 사용하고, 세탁기에 넣기 전에 안내사항을 꼭 확인해주긔!\n\n7. 타사와의 차별점 설명\n- 우리 맨투맨은 고품질의 소재와 섬세한 디자인으로 타사와의 차별점을 가지고 있음! 편안한 착용감과 멋진 디자인을 동시에 느낄 수 있음!\n\n8. 다시 한번 제품 구성과 가격 강조\n- 회색맨투맨과 파란맨투맨은 각각 30,000원에 판매되고 있음! 가격에 비해 훌륭한 품질을 제공하고 있으니, 이 기회를 놓치지 말긔!'),(1,0,0,'2024-02-16 00:35:29.389159','2024-02-16 00:36:24.268151','2024-02-16 00:35:50.915534','2024-02-17 00:35:00.000000',4,'포도향이 가득! 말랑말랑 마이구미 젤리!','https://i10a402.p.ssafy.io/openvidu/recordings/AoZ1Z9Zzsg/AoZ1Z9Zzsg.mp4','AoZ1Z9Zzsg','LC6n6E5KpHOn','1. 오프닝 / 인사\n안녕~ 여러분! 소연이야! 오늘은 맛있는 포도향 마이구미 젤리 소개해줄거야! 우리 제품은 포도과즙을 그대로 사용해서 진짜 포도 맛을 즐길 수 있는 거야!\n\n2. 상품소개 / 라이브 방송 혜택 안내\n이번 판매에서는 특별히 10% 할인된 가격으로 제품을 판매할 거야! 또, 라이브 방송 시간 동안에만 구매하시는 분들께는 추가로 선물도 드릴 거니까 꼭 놓치지 마셈!\n\n3. 상품 구성 소개 / 포장 상태 설명 / 크기 비교\n이 마이구미 젤리는 한 팩에 10개의 젤리가 들어있어! 포장은 식품 등급의 투명한 포장지로 되어 있어서 신선함을 유지할 수 있어! 크기는 한 개의 젤리가 약 2cm 정도이니 참고해봐!\n\n4. 제품의 특징 강조\n이 마이구미 젤리는 포도과즙을 그대로 사용하여 진짜 포도 맛을 느낄 수 있어! 그리고 식품 등급의 원료를 사용하여 안전하게 제조되었어! 입안에서 살살 녹아내리는 식감과 함께, 달콤한 포도 맛이 입안 가득 퍼질 거야!\n\n5. 제품을 활용할 수 있는 여러 방법 / 사용하는 용도\n이 마이구미 젤리는 다양한 용도로 활용할 수 있어! 단순히 간식으로 먹는 것은 물론, 케이크 장식이나 디저트에 활용해도 좋아! 또, 아이들의 간식으로도 안전하게 즐길 수 있어!\n\n6. 제품 사용 방법 안내\n이 마이구미 젤리는 개별 포장되어 있어서 휴대하기에도 편리해! 포장지를 열어서 바로 먹을 수 있고, 신선한 상태를 유지하기 위해 빨리 섭취해주는 게 좋아!\n\n7. 타사와의 차별점 설명\n우리 포도향 마이구미 젤리는 포도과즙을 그대로 사용하여 진짜 포도 맛을 느낄 수 있어! 또한, 식품 등급의 원료를 사용하여 안전하게 제조되었어! 다른 제품과는 다르게 진짜 포도 맛을 즐길 수 있는 것이 큰 차별점이야!\n\n8. 다시 한번 제품 구성과 가격 강조\n한 팩에는 10개의 포도향 마이구미 젤리가 들어있고, 오늘만 10% 할인된 가격으로 판매돼! 라이브 방송 시간 동안에만 구매하시는 분들께는 추가로 선물도 드릴 예정이니까 꼭 놓치지 마!'),(1,1,0,'2024-02-15 23:51:22.519626',NULL,'2024-02-15 23:58:40.959144','2024-02-16 23:51:00.000000',4,'오래 끼고 있어도 귀가 편한 유선 이어폰',NULL,'BdKehKDVj3','kIHqpHc3KT9M','1. 오프닝~ 인사!\n- 안녕~! 오늘은 멋진 유선 이어폰을 소개해줄거야~! 난 김소연이지! 함께 즐거운 시간 보내시길 바래~!\n\n2. 상품소개 / 라이브 방송 혜택 안내!\n- 요즘은 무선 이어폰이 인기지만, 우리는 유선 이어폰을 더 추천해! 왜냐면 유선 이어폰은 음질이 더 좋고, 오래 착용해도 편안한 인체 공학적 디자인이거든! 오늘은 이 멋진 유선 이어폰을 특별한 혜택과 함께 소개해줄거야!\n\n3. 상품 구성 소개 / 포장 상태 설명 / 크기 비교!\n- 이 유선 이어폰은 패키지에 포함된 이어폰과 이어팁, 사용 설명서로 구성되어 있어! 포장 상태는 신선한 상태로 보내드리고, 크기는 휴대하기 편한 사이즈로 제작되었어!\n\n4. 제품의 특징 강조!\n- 이 유선 이어폰은 우수한 음질을 제공해! 더불어 인체 공학적 디자인으로 귀에 편안하게 착용할 수 있어! 또한, 외부 소음을 차단하여 좋은 음악 감상 환경을 제공해!\n\n5. 제품을 활용할 수 있는 여러 방법 / 사용하는 용도!\n- 이 유선 이어폰은 음악 감상뿐만 아니라 영화 시청, 게임 플레이 등 다양한 용도로 활용할 수 있어! 어떤 용도로 사용하든, 탁월한 음질을 경험하실 수 있을거야!\n\n6. 제품 사용 방법 안내!\n- 유선 이어폰의 사용 방법은 매우 간단해! 이어폰을 이어팁에 연결하고, 이어폰을 귀에 착용하면 돼! 특별한 설정이 필요 없어 편리하게 사용할 수 있어!\n\n7. 타사와의 차별점 설명!\n- 다른 무선 이어폰과 비교했을 때, 이 유선 이어폰은 더욱 탁월한 음질과 편안한 착용감을 제공해! 또한, 외부 소음을 차단하여 좋은 음악 감상 환경을 제공해! 이러한 차별점으로 인해 많은 분들께서 유선 이어폰을 선호하시는 추세야!\n\n8. 다시 한번 제품 구성과 가격 강조!\n- 이 유선 이어폰은 이어폰과 이어팁, 사용 설명서로 구성되어 있으며, 특별한 혜택과 함께 이용할 수 있어! 가격은 XX,XXX원으로 저렴하게 제공돼! 이 가격에 이런 훌륭한 제품을 만나실 수 있다니, 정말 좋은 거래라고 생각해!'),(1,0,0,'2024-02-16 03:52:10.157859',NULL,NULL,'2024-02-19 03:52:00.000000',31,'[보라카이] 보라카이로 휴양을 떠나보아요✈',NULL,'mg2Fo4gaxO','RDqO6GI7eNhr',''),(1,0,0,'2024-02-16 02:11:56.214727','2024-02-16 02:27:10.242958','2024-02-16 02:23:59.468569','2024-02-16 02:11:00.000000',31,'엄마도 칭찬한 그릇 사세요','https://i10a402.p.ssafy.io/openvidu/recordings/MhOIjmxGno/MhOIjmxGno.mp4','MhOIjmxGno','pSgqvWqKklVe',''),(1,0,0,'2024-02-16 09:10:36.136290','2024-02-16 09:11:15.805080','2024-02-16 09:10:50.256609','2024-02-16 09:10:00.000000',3,'[-3Kg]날씬해보이는 청바지','https://i10a402.p.ssafy.io/openvidu/recordings/qjaoPqyL3Y/qjaoPqyL3Y.mp4','qjaoPqyL3Y','3wtlN3txWpPT',''),(1,0,0,'2024-02-16 08:35:01.727474','2024-02-16 08:42:06.311714','2024-02-16 08:37:16.018403','2024-02-16 08:34:00.000000',1,'요즘 대세는 바로 마라탕후루~!~!','https://i10a402.p.ssafy.io/openvidu/recordings/qjaoSffx3Y/qjaoSffx3Y.mp4','qjaoSffx3Y','8inOTPiHR0I7',''),(1,0,0,'2024-02-16 03:06:39.488887','2024-02-16 03:13:21.395585','2024-02-16 03:11:38.004402','2024-02-16 03:06:00.000000',31,'지우X핑크 앙고라 니트','https://i10a402.p.ssafy.io/openvidu/recordings/QxbTwBuwAc/QxbTwBuwAc.mp4','QxbTwBuwAc','OkxcMbQrJLhN',''),(0,1,0,'2024-02-16 02:57:11.652676',NULL,'2024-02-16 02:57:57.721277','2024-02-16 02:56:00.000000',31,'못난이 약과 팔구있다9',NULL,'tQiYnYxCyv','YTL8vsYpETxC',''),(0,0,0,'2024-02-16 01:26:40.457465',NULL,NULL,'2024-02-16 01:30:00.000000',31,'폭신한 니트 팝니다. 색 다양해요!!',NULL,'WfyozPBT0j','QxbTJ4J4A48c',''),(1,1,0,'2024-02-15 22:55:04.307953',NULL,'2024-02-15 23:15:55.995977','2024-02-16 22:54:00.000000',4,'가성비 갑! 추천 아크로 프로버즈 무선 이어폰',NULL,'ZE4CpCVAMd','slM1h1Gk8jaG',''),(0,0,0,'2024-02-16 01:37:40.316314',NULL,NULL,'2024-02-16 02:37:00.000000',31,'제주에서 온 한라봉 맛보시면 재구매 각!',NULL,'ZE4SPrsiMd','slM7CTjo8jaG','');
/*!40000 ALTER TABLE `live` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livelog`
--

DROP TABLE IF EXISTS `livelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livelog` (
  `end` time(6) DEFAULT NULL,
  `start` time(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `title` varchar(30) DEFAULT NULL,
  `live_id` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjgkyt3k1o8dmxuqtlvx16c070` (`live_id`),
  CONSTRAINT `FKjgkyt3k1o8dmxuqtlvx16c070` FOREIGN KEY (`live_id`) REFERENCES `live` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livelog`
--

LOCK TABLES `livelog` WRITE;
/*!40000 ALTER TABLE `livelog` DISABLE KEYS */;
INSERT INTO `livelog` VALUES ('00:03:12.000000','00:00:00.000000',74,'시도와 실패','MhOIjmxGno'),('00:00:17.000000','00:00:11.000000',79,'블랙티 유스 이니스프리 앰플 소개','13ABCR5'),('00:00:38.000000','00:00:30.000000',80,'제품의 특징','13ABCR5'),('00:01:03.000000','00:00:47.000000',81,'구성 설명','13ABCR5'),('00:01:19.000000','00:01:12.000000',82,'가격 안내','13ABCR5'),('00:00:56.000000','00:00:54.000000',83,'포트폴리오와 SNS 중요성','QxbTwBuwAc'),('00:01:10.000000','00:01:00.000000',84,'포트폴리오 제작 방법','QxbTwBuwAc'),('00:00:56.000000','00:00:39.000000',85,'상품 설명','qjaoSffx3Y'),('00:03:04.000000','00:02:56.000000',86,'상품 가격','qjaoSffx3Y'),('00:03:18.000000','00:03:12.000000',87,'구매 방법','qjaoSffx3Y'),('00:00:08.000000','00:00:08.000000',96,'청바지의 특징','qjaoPqyL3Y'),('00:00:08.000000','00:00:08.000000',97,'한정수량','qjaoPqyL3Y'),('00:00:20.000000','00:00:10.000000',120,'잉','31ABCR5'),('00:00:05.000000','00:00:02.000000',123,'ㅇㅇ','22ABCR5'),('00:00:10.000000','00:00:06.000000',124,'ㄴㅇㄹㄴㄹㅇ','22ABCR5'),('00:00:00.000000','00:00:00.000000',125,'라이브 시작','8inOYlHbR7'),('00:00:12.000000','00:00:12.000000',126,'채팅 구성','8inOYlHbR7'),('00:00:32.000000','00:00:32.000000',127,'질문 리스트업','8inOYlHbR7'),('00:01:01.000000','00:01:01.000000',128,'라이브 종료','8inOYlHbR7'),('00:00:26.000000','00:00:00.000000',129,'상품 등록대 및 채팅 기능 소개','3wtloRgaWT'),('00:00:46.000000','00:00:26.000000',130,'질문에 대한 답변 기능 소개','3wtloRgaWT'),('00:01:06.000000','00:01:01.000000',131,'상품 특징 설명','3wtloRgaWT'),('00:01:09.000000','00:01:08.000000',132,'채팅을 통한 판매자와의 대화','3wtloRgaWT');
/*!40000 ALTER TABLE `livelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livelog_seq`
--

DROP TABLE IF EXISTS `livelog_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livelog_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livelog_seq`
--

LOCK TABLES `livelog_seq` WRITE;
/*!40000 ALTER TABLE `livelog_seq` DISABLE KEYS */;
INSERT INTO `livelog_seq` VALUES (133);
/*!40000 ALTER TABLE `livelog_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `birth` date DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `deleted_at` timestamp NULL DEFAULT NULL,
  `id` bigint NOT NULL,
  `bank` varchar(10) DEFAULT NULL,
  `channel` varchar(10) NOT NULL,
  `name` varchar(10) NOT NULL,
  `phone` varchar(11) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `address_detail` varchar(50) DEFAULT NULL,
  `account` varchar(64) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `token` varchar(256) DEFAULT NULL,
  `email` varchar(320) NOT NULL,
  `profile` varchar(2083) DEFAULT NULL,
  `gender` enum('M','F','N') DEFAULT NULL,
  `login_type` enum('email','kakao','naver','google') DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('2001-12-11','2017-12-12 03:47:46',NULL,1,'우리','ROSE','김로제','01043728973','경기 용인시 처인구 포곡읍 에버랜드로 199','에버랜드','EcvVMFnTsLgGCGoKhms8bw==','김로제의 김로오오제채널입니다.','$2a$10$oyMCmb6bNyr5VhTOgjnZP.8G66o6c9PuUQ1Vo3SLM4q08NN2VI5nW','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MSwiZW1haWwiOiJxbGVuZnJsMTAxQG5hdmVyLmNvbSIsInJvbGUiOiJDT01NT04iLCJpYXQiOjE3MDgwNDYzNzQsImV4cCI6MTc5NDQ0NjM3NH0.L0qeycc86IpRYciI1M6X2yN2_9rHhDlCjFVHqhQiALk','qlenfrl101@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/1.jpg','F','email'),('1994-06-12','2019-01-30 12:27:43',NULL,2,'신한','JENNIE','최제니','01023768332','서울 중구 세종대로 40','숭례문','tBmnp9aa95DZLfnIhdg9Ug==','최제니의 개인채널입니다.','$2a$10$LhQI1.0OWHmtqqx8dkaaHuHGrM688.J9lQxjFziEkTrY7A3fRrEsm',NULL,'wjsrudgid@naver.com ','https://pasila.s3.ap-northeast-2.amazonaws.com/member/2.jpg','F','email'),('1992-02-13','2022-02-10 12:42:16',NULL,3,'국민','KARINA','유리나','01001478922','서울 중구 을지로 281','동대문디자인플라자','ipQgkBHiREcilk8MwpQkfQ==','안녕하세요 좋은 상품 신속한 배송으로 보답하겠습니다.','$2a$10$GAwi2A23CB8BoedRnwJAXu25rFoSKBj8OZJOBq2IPcudZwcWk8STK','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MywiZW1haWwiOiJrYXJpbmEwMEBuYXZlci5jb20iLCJyb2xlIjoiQ09NTU9OIiwiaWF0IjoxNzA4MDQ2MDM5LCJleHAiOjE3OTQ0NDYwMzl9.h3Jq2yUHDJ672k-itbnZILN9ck6seCAJmq_gfgVwfAU','karina00@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/3.jpg','F','email'),('1996-01-04','2022-04-07 09:27:22',NULL,4,'신한','감나라농원','김소연','01098736578','서울특별시 서초구 올림픽대로 2085-14','세빛섬','LBvkjfb0tfVM0iMfAMUGVg==','직접 수확한 사과를 판매하고 있습니다!!','$2a$10$MXF6zO1c6UGx/JpEbWOosOrpnXD2A47cFujohG2tho2xeavWg7ULi','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6NCwiZW1haWwiOiJzeTExMUBuYXZlci5jb20iLCJyb2xlIjoiQ09NTU9OIiwiaWF0IjoxNzA4MDQzNjE2LCJleHAiOjE3OTQ0NDM2MTZ9.FKRP2Nse4V3Tm3AgGbI5h5jFkv7gtxgTnWxgMWKp7z8','sy111@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/4.jpg','F','email'),('1987-02-15','2023-01-06 10:19:12',NULL,5,'농협','이미테이션','최동헌','01019983201','서울특별시 용산구 서빙고로 137','국립중앙박물관','M+m9viyB8xXjZsnD5cSMZA==','가품일 경우 100배로 보장 해드립니다.','$2a$10$00PCkElTccOhzKkMqMIeru7Y9ka1wAXdvdiEbpgvTCM3cvJmWbJaC','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6NSwiZW1haWwiOiJlaGRnanM5OTg5QG5hdmVyLmNvbSIsInJvbGUiOiJDT01NT04iLCJpYXQiOjE3MDgwMDYyNzcsImV4cCI6MTc5NDQwNjI3N30.fLOZAkpfdStYX-JfMAlpIGZSyO7eV7tuJ-Dambyx-10','ehdgjs9989@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/5.jpg','M','email'),('1988-01-16','2023-02-16 01:07:11',NULL,6,'기업','의류수거함','유동헌','01089333299','서울 종로구 삼청로 30','국립현대미술관 서울','8MRpwWAKcWNmxHVCgVC9HQ==','어디서도 보지 못한 디자인의 옷으로 승부합니다. ','$2a$10$gg0SIb0GB6DE5nCEpx.I6.hMBo/gFMcZH/OK.hN11h3EdsXq/78Mm','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6NiwiZW1haWwiOiJkaGVvbjc3QGdtYWlsLmNvbSIsInJvbGUiOiJDT01NT04iLCJpYXQiOjE3MDgwNDM5MzcsImV4cCI6MTc5NDQ0MzkzN30.SxUrXsIOfxQEo8KFoLjD8pxdSLyaGpQV6cECzaqCIdQ','dheon77@gmail.com','https://cdn.pixabay.com/photo/2020/05/17/20/21/cat-5183427_1280.jpg','M','email'),('1990-04-17','2023-11-11 11:22:18',NULL,7,'하나','오동나무','최경향','01030912890','서울 광진구 능동로 216','어린이대공원','i8zZJ6cl7PpyAbJPwA944w==','100프로 수제작 목공예품만 취급합니다.','$2a$10$5btkR8m8om2PMTg8j/QZ/uMcm12Osh7cA.bMT0gpf4j2oMX4kEzFG',NULL,'rud223@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/7.jpg','F','email'),('1999-03-18','2023-12-25 12:25:12',NULL,8,'우리','치악산복숭아','김청년','01098347211','서울특별시 강남구 테헤란로7길 22','한국 과학기술회관','UygMRRXj/L6Dh0+JIQ3U4A==','치악산 복숭아 당도최고','$2a$10$C7axTSqP3lx8.JpKA1AD9.uR7knBDbQIdLY4o29rv8cWiDEYpsd4S','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6OCwiZW1haWwiOiJydWRnaWQxMjNAZ21haWwuY29tIiwicm9sZSI6IkNPTU1PTiIsImlhdCI6MTcwODA0NTI1OCwiZXhwIjoxNzk0NDQ1MjU4fQ.06Iwv91lLasHJ1yt-r3Q5eOCf22Lm-ULYaFz-pOEc1Q','rudgid123@gmail.com','https://pasila.s3.ap-northeast-2.amazonaws.com/images/8.jpg','F','email'),('2001-02-19','2024-01-01 11:27:38',NULL,9,'국민','자동차신발','정유경','01032542253','서울 종로구 삼청로 30','국립민속박물관','y+kTf7JIcyrhF9ud3MoMtQ==','앗! 타이어 신발보다 싸다.','$2a$10$p/bLN7BzKNy6VWAOGPEssezMcNx8lFn0hWBvDwEKvUux4QtLOrJ02',NULL,'yk1212@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/9.jpg','F','email'),('1997-05-10','2024-02-07 15:20:19',NULL,10,'국민','무우신사','박지원','01032124513','서울 서초구 반포대로 201','국립중앙도서관','5XUuaPS1fCMjuOxkffKTEg==','무진장 신발 사진이 많은곳','$2a$10$veFxowgyFMVj7ki0xJnMbOE03kyG/NWrLj1FkW0OL/bIoKc1oc3ES',NULL,'wldnjs3211@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/10.jpg','F','email'),('2024-02-06','2024-02-16 00:02:19',NULL,31,'','유굥이의 채널입니다','정유경','01083486698','서울 강남구 테헤란로 212','(역삼동)','vFjNqUxLzfsK17GxYRbnyw==',NULL,'$2a$10$EVqaMl7K6emuQvo8cH6HZ.mBIP8T8gzNT7n47vWDSwpKtbPziSX7S','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MzEsImVtYWlsIjoieXlrODM0QG5hdmVyLmNvbSIsInJvbGUiOiJDT01NT04iLCJpYXQiOjE3MDgwMjcwODIsImV4cCI6MTc5NDQyNzA4Mn0.oP7kbh-lk5JwbKAzEdahkr_ASFDJrfAw87df0q9uMF4','yyk834@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/images/31.jpg','F',NULL),('1999-08-05','2024-02-16 10:19:40',NULL,32,NULL,'청년말투','안녕하냥','01075438689','','',NULL,NULL,'$2a$10$pJW.eUZc922N45usyxQvReWlt6S7mI3Qusyjm.yV9A2s2MygMxyjG','eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZCI6MzIsImVtYWlsIjoieWVldW5qZW9uNzg5QGdtYWlsLmNvbSIsInJvbGUiOiJDT01NT04iLCJpYXQiOjE3MDgwNDY0MDYsImV4cCI6MTc5NDQ0NjQwNn0.88WZJI_-qgkdNLriaPRgfealsQnAy3hQrXJqycK0hXo','yeeunjeon789@gmail.com',NULL,'F',NULL);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_seq`
--

DROP TABLE IF EXISTS `member_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_seq`
--

LOCK TABLES `member_seq` WRITE;
/*!40000 ALTER TABLE `member_seq` DISABLE KEYS */;
INSERT INTO `member_seq` VALUES (33);
/*!40000 ALTER TABLE `member_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_cnt` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `id` bigint NOT NULL,
  `member_id` bigint DEFAULT NULL,
  `product_option_id` bigint DEFAULT NULL,
  `name` varchar(10) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `status` enum('CANCEL','ORDER','DEPOSIT','READY','START','COMP') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpktxwhj3x9m4gth5ff6bkqgeb` (`member_id`),
  KEY `FK7y7p3vcskivqy8tu9eu3xxx3w` (`product_option_id`),
  CONSTRAINT `FK7y7p3vcskivqy8tu9eu3xxx3w` FOREIGN KEY (`product_option_id`) REFERENCES `product_option` (`id`),
  CONSTRAINT `FKpktxwhj3x9m4gth5ff6bkqgeb` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,10000,'2024-02-15 21:16:04.172905',NULL,31,1,8,'김로제','경기 용인시 처인구 포곡읍 에버랜드로 199 에버랜드','ORDER'),(1,1590000,'2024-02-15 22:46:20.090881',NULL,32,4,1,'김소연','서울특별시 서초구 올림픽대로 2085-14 세빛섬','DEPOSIT'),(1,50660,'2024-02-16 00:00:16.731981',NULL,33,8,62,'전경향','서울특별시 강남구 테헤란로7길 22 한국 과학기술회관','ORDER'),(1,50660,'2024-02-16 00:03:57.637765',NULL,34,31,62,'정유경','서울 강남구 테헤란로 212 (역삼동)','ORDER'),(1,50660,'2024-02-16 00:05:18.959471',NULL,35,8,62,'전경향','서울특별시 강남구 테헤란로7길 22 한국 과학기술회관','ORDER'),(2,54000,'2024-02-16 00:42:01.549512',NULL,36,1,63,'김로제','경기 용인시 처인구 포곡읍 에버랜드로 199 에버랜드','ORDER'),(1,13950,'2024-02-16 08:56:37.664275',NULL,37,1,76,'김로제','경기 용인시 처인구 포곡읍 에버랜드로 199 에버랜드','ORDER'),(1,27000,'2024-02-16 09:27:05.337052',NULL,38,8,63,'김청년','서울특별시 강남구 테헤란로7길 22 한국 과학기술회관','ORDER');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_seq`
--

DROP TABLE IF EXISTS `orders_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_seq`
--

LOCK TABLES `orders_seq` WRITE;
/*!40000 ALTER TABLE `orders_seq` DISABLE KEYS */;
INSERT INTO `orders_seq` VALUES (39);
/*!40000 ALTER TABLE `orders_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `is_active` tinyint(1) DEFAULT '1',
  `category_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `thumbnail` varchar(2083) DEFAULT NULL,
  `id` varchar(12) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  KEY `FKnlsnjukov4vm4dv1dw0l438hi` (`member_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `FKnlsnjukov4vm4dv1dw0l438hi` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,1,'2024-02-15 11:21:19.000000',1,'2024-02-15 11:21:19.000000','리쥬란 홈케어 뷰티 디바이스 리쥬리프','https://shop-phinf.pstatic.net/20230720_6/1689817112095ceQeo_JPEG/19229125731850418_1728260416.jpg?type=o1000','11FD88R5Y5XM','홈케어의 한계를 잊게 만드는 진짜 뷰티 디바이스.\n 탄력 루틴으로 완성하는 얼굴라인 4주만에 나타나는 콜라겐 리모델링 효과! 하루 20분 초음파 고주파 카트리지로 처진 피부 더블업 케어!\n 사용법: 1. 타겟 초음파 카트리지 장착 후 부스팅 젤을 도포해주세요. 2. 카트리지를 피부에 밀착하여 케어합니다.'),(1,1,'2024-02-15 11:21:19.000000',2,'2024-02-15 11:21:19.000000','프리메라 레티놀 볼륨 립 세럼 12g','https://shop-phinf.pstatic.net/20240129_145/1706518604447vlh7F_JPEG/33821288328759541_1278530703.jpg?type=o1000','12FD88R5Y5XM','최초 레티놀 립 세럼 리얼 볼륨 오버립. 사용할수록 탄력있게 차오르는 볼륨감으로 도톰하고 탱글한 입술! 사용 직후에도 자연스럽고 시원한 플럼핑 룩. 고밀도 젤의 높은 밀착력으로 윤기나는 입술까지!'),(1,1,'2024-02-15 11:21:19.000000',1,'2024-02-15 11:21:19.000000','이니스프리 블랙티 유스 인핸싱 앰플','https://shop-phinf.pstatic.net/20240108_48/1704640507827pWrhb_JPEG/31943191712481902_2058059907.jpg?type=o1000','13FD88R5Y5XM','블랙티 유스 인핸싱 앰플. 복합적인 피부 고민을 단 한병으로 케어! 제주 블랙티에서 추출한 reset concentrate를 함유해 강력한 피부 컨디션 케어. 숫자로 증명하는 진짜 안티에이징! 한번만 써봐도 다음날 톤, 윤기, 결이 달라지는게 느껴져요.'),(1,2,'2024-02-15 11:21:19.000000',3,'2024-02-15 11:21:19.000000','기장끝집 오직 전복죽 밀키트 전복내장 간편 영양죽','https://shop-phinf.pstatic.net/20230224_191/1677227623607TrGQP_PNG/78363522290870889_859664421.png?type=o1000','21FD88R5Y5XM','누적 판매 1,000,000팩 이상 누적 후기 19,000건 이상! 오직 전복죽만 판매하여 전복죽 맛집으로 인정받았습니다. 전복과 전복내장 손질부터 전복죽 밀키트 생산과 냉동 과정까지도 전복죽의 모든 과정에서 확실한 차이를 만듭니다.'),(1,2,'2024-02-15 11:21:19.000000',8,'2024-02-15 11:21:19.000000','고당도 제철 성주 꿀참외 주먹참외 용암참외','https://shop-phinf.pstatic.net/20230215_200/1676455193892Xjyx3_JPEG/77591089614524128_1527759179.jpg?type=o1000','22FD88R5Y5XM','금메달 농산물 성주 참외입니다. 농장에서 당일수확 당일발송! 금메달농산물 봄과 여름하면 생각나는 과일은 참외!! 저희 금메달 농산물의 자랑거리인 제철과일 성주 참외에 대해 아시나요? 당도가 매우 우수한 성주참외입니다. 참외는 성주참외가 대중적으로 널리 알려져 있습니다. 그 중에서도 성주참외는 브랜드인만큼 다른 일반 성주참외보다 월등히 우수한맛이라고 여러분들께 자신있게 말할 수 있습니다.'),(1,2,'2024-02-15 11:21:19.000000',3,'2024-02-15 11:21:19.000000','콩고미 식물성 단백질 바삭바삭 고소한 야채볶음밥 누릉지','https://shop-phinf.pstatic.net/20230914_157/1694667077197UAT74_JPEG/2014807073929737_1772424659.JPG?type=o1000','23FD88R5Y5XM','맛있고 고소하고 바삭한맛 다양한 야채가 듬뿍 야채볶음밥 누릉지. 살찔까봐 먹지도 못한 누룽지 이젠 마음껏 드세요! 감칠맛이 뛰어난 야채볶음밥 누룽지. 고단백, 저탄수화물, 저지방, 저칼로리'),(1,3,'2024-02-15 11:21:19.000000',6,'2024-02-15 11:21:19.000000','딥블랙 / 슬림 스트레이트 블랙 스판 데님 팬츠','https://shop-phinf.pstatic.net/20240214_32/1707878907640g8OMu_JPEG/109014742448951177_267551439.jpg?type=o1000','31FD88R5Y5XM','- 컬러 : 블랙 - 소재 : 면97%, 스판3%\n-특징 : 딥한 블랙컬러로 착용시 슬림해보이는 깔끔하고 부드러운 감촉이 돋보이는 블랙 팬츠. 밴딩이 드러나지 않는 히든허리밴딩 디자인. 아주 편하며 다리 전체도 스판성이 뛰어나 편안합니다. 밑위길이도 낙낙하여 허리 들뜸없이 핏됩니다.\n-사이즈 : S,M,L'),(1,3,'2024-02-15 11:21:19.000000',1,'2024-02-15 11:21:19.000000','애니베어 레터링 프린팅 루즈핏 롱 봄 카라셔츠','https://shop-phinf.pstatic.net/20240208_114/1707321250204axg1D_JPEG/108457085026479901_114507757.jpg?type=o1000','32FD88R5Y5XM','귀엽고 특이한 디자인으로 눈에 확~띄는 곰돌이 프린팅 언발 기장 롱 셔츠 보여드릴게요! 부드럽고 하늘하늘거리는 소재라 피부에 챠르르 닿는 감촉이 좋고 구김이 덜해 휘뚜루마뚜루 활용하기 좋은 아이템이랍니다! 전체적으로 여유있는 루즈핏에 롱한 기장감으로 언니들의 미운 군살들을 쏙! 여리여리한 핏으로 연출해주어용. Color: 화이트, 핑크  FABRIC: 폴리혼방  SIZE: free'),(1,3,'2024-02-15 11:21:19.000000',2,'2024-02-15 11:21:19.000000','콘드 홀가먼트 라운드넥 울 니트','https://shop-phinf.pstatic.net/20231117_155/1700225587078ajuFH_JPEG/27052852278162305_1703841395.jpg?type=o1000','33FD88R5Y5XM','피부에 닿는 부드러움이 너무 고급스러운 울 혼방소재의 니트로 울의 포근함과 따뜻함이 느껴지는 제품이에요. 봉제선 없이 통으로 편직되는 홀가먼트 가공법으로 피부가 예민하신 분들에게 더욱 좋은 하이퀄리티 니트입니다. 두껍지 않은 슬림한 원단으로 부해 보이지 않고 깔끔하게 떨어지는 스타일이라 아우터 안에 가볍게 입기 좋습니다. 사이즈 free 울80% 나일론20% 색상: 핑크, 베이지, 블루'),(1,3,'2024-02-16 09:10:36.119290',3,'2024-02-16 09:10:36.137918','날씬해보이는청바지','https://pasila.s3.ap-northeast-2.amazonaws.com/images/3wtlN3txWpPT.jpg','3wtlN3txWpPT','<p class=\"ql-align-center\">PRODUCT DESCRIPTION</p><p class=\"ql-align-center\"></p><p class=\"ql-align-center\">· 유려하게 흐르는 느낌을 주는 여름 원단 사용</p><p class=\"ql-align-center\">· 통기성이 우수해 여름 시즌에 시원하게 착용 가능</p><p class=\"ql-align-center\">· 길고 슬림해 보이는 일자 와이드 핏</p><p></p>'),(1,4,'2024-02-15 11:21:19.000000',7,'2024-02-15 11:21:19.000000','네르하 이케바나 화병 이케바나롱 대형 유리 화병 꽃병','https://shop-phinf.pstatic.net/20220329_98/1648520298936YVwrt_JPEG/49656133511298966_1211701576.jpg?type=o1000','41FD88R5Y5XM','유명 브랜드 제품도 식물, 물에 의한 변색이 나타나고 저가형 중국산 황동 도금 이케바나 화병에서는 빈번하게 부식과 변색이 발생합니다. 그래서 직접 만들었습니다. 100% 스테인리스 국내 제작 변색, 부식 없는 이케바나 화병! 취향에 따라 더 다양하게 선택할 수 있도록 라운드, 롱, 웨이브 옵션이 있습니다. 고급 중대형 이케바나 입니다. 선물용으로 좋습니다. 특별한 선물, 집들이 선물로 추천합니다.'),(1,4,'2024-02-15 11:21:19.000000',7,'2024-02-15 11:21:19.000000','골드 부엉이장식품 인테리어 풍수 소품','https://shop-phinf.pstatic.net/20200901_85/1598890222080UNsqa_JPEG/36251764720864531_1427925739.JPG?type=o1000','42FD88R5Y5XM','골드 부엉이 장식품과 부엉이 문종은 선물 포장이 가능합니다. 어디에서나 볼 수 있는 흔한 포장이 아닌, 고급스러운 선물상자는 여기서만 볼 수 있죠. 소중한 마음과 예쁨을 듬뿍 담아 보내드립니다. 실제로 보시면 반짝이는 큐빅스톤장식이 더욱 빛을 발합니다. 선물로 굉장히 인기가 많아요'),(1,2,'2024-02-16 08:35:01.702695',1,'2024-02-16 08:35:01.730908','로제떡볶이 밀키트','https://pasila.s3.ap-northeast-2.amazonaws.com/images/8inOTPiHR0I7.jpg','8inOTPiHR0I7','<p></p><p></p>'),(1,1,'2024-02-16 08:50:58.961301',1,'2024-02-16 08:50:58.989755','아비브 거미 시트 마스크','https://pasila.s3.ap-northeast-2.amazonaws.com/images/AoZd7wwEs1ig.jpg','AoZd7wwEs1ig','<p></p><p></p><p>엄청 유명한 마스크인거 다들 아시죠?</p><p></p><p>지금 안사면 후회합니다!</p><p></p>'),(1,2,'2024-02-16 01:57:42.138206',31,'2024-02-16 01:57:42.185371','쫀득한 약과','https://pasila.s3.ap-northeast-2.amazonaws.com/images/h7keT7CiBvQ8.jpg','h7keT7CiBvQ8','<p></p><p></p><p></p>'),(1,3,'2024-02-16 10:16:57.071741',3,'2024-02-16 10:16:57.085312','맨투맨','https://pasila.s3.ap-northeast-2.amazonaws.com/images/Iec9MRx9vBlc.jpg','Iec9MRx9vBlc','<p></p><p></p>'),(1,6,'2024-02-15 23:51:22.495632',4,'2024-02-15 23:51:22.523530','무선보다 편한 유선이어폰','https://pasila.s3.ap-northeast-2.amazonaws.com/images/kIHqpHc3KT9M.jpg','kIHqpHc3KT9M','<p>무선은 가라.</p><p></p>'),(1,2,'2024-02-16 00:35:29.331700',4,'2024-02-16 00:35:29.396628','포도향이 가득, 마이구미 젤리','https://pasila.s3.ap-northeast-2.amazonaws.com/images/LC6n6E5KpHOn.jpg','LC6n6E5KpHOn','<p></p><p></p><p>포도과즙을 그대로 사용한 마이구미 젤리</p><p></p><p>마이구미는 맛있어요!</p>'),(1,3,'2024-02-16 03:06:39.460415',31,'2024-02-16 03:06:39.491769','앙고라니트','https://pasila.s3.ap-northeast-2.amazonaws.com/images/OkxcMbQrJLhN.jpg','OkxcMbQrJLhN','<p>헤어리한 외관이 부드러운 분위기를 연출하는 앙고라 골지 입니다. 부드럽고 유연한 소재를 사용하여 착용감이 편안합니다. 선명한 색상이 얼굴을 환하게 밝혀주고 다양한 룩에 포인트가 되어줍니다.</p><p>소재정보</p><p>겉감 : 모 44%, 나일론 35%, 앙고라 21%.</p><p></p><p>모델 착용 이미지보다 제품컷 이미지의 컬러가 정확합니다.</p><p></p>'),(1,2,'2024-02-16 02:11:56.193081',31,'2024-02-16 02:11:56.216904','마라탕밀키트❤','https://pasila.s3.ap-northeast-2.amazonaws.com/images/pSgqvWqKklVe.jpg','pSgqvWqKklVe','<p></p><p></p><p></p><p></p><p>마라 입문자도 충분히 드실 수 있게 만들었습니다. 시중에 판매되는 마라탕 기름에서 대부분의 기름을 뺐고 다진 마늘을 추가했습니다. 때문에 마라를 먹고 속쓰리고 배아파 화장실에서 고생하실 일 없게끔 만들어봤습니다.</p><p>(개개인에 따라 다르겠지만, 테스트 중에 처음엔 배앓이 하셨던 분들이 완성품에선 대부분 배앓이를 하지 않으셨어요. 100% 모두 다 배 안아플거라고 말씀을 못드리지만, 배 안아프도록 최대한 노력해보았어요)</p><p></p><p></p><p>밀키트 출시 직전까지도 고민을 많이 했는데요. 정말 냉정했던 많은 시식체험단 분들께서 완성품에 흡족해하시고 극찬해주셔서 출시를 결정하게 되었습니다. </p><p></p><p>많은 판매량 보다도 누군가에게 최고의 마라전골 마라탕이 되길 바라는 마음으로 준비했습니다. 믿고 주문해주신 분들께 큰 만족감과 행복이 전해졌으면 좋겠습니다.</p><p></p><p>여기서 더 말하면.. 사연팔이가 되므로 이만 줄이겠습니다. 여러분 정말 감사드리고 너무너무 고맙습니다. 진심으로 감사합니다</p><p></p>'),(1,2,'2024-02-16 01:26:40.430721',31,'2024-02-16 01:26:40.460475','겉바속촉약과','https://pasila.s3.ap-northeast-2.amazonaws.com/images/QxbTJ4J4A48c.jpg','QxbTJ4J4A48c','<p></p><p></p><p></p><p></p>'),(1,5,'2024-02-16 03:52:10.111715',31,'2024-02-16 03:52:10.161072','보라카이패키지','https://pasila.s3.ap-northeast-2.amazonaws.com/images/RDqO6GI7eNhr.jpg','RDqO6GI7eNhr','<p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p><p></p>'),(1,6,'2024-02-15 22:55:04.283536',4,'2024-02-15 22:55:04.310459','아크로 프로버즈 무선 이어폰','https://pasila.s3.ap-northeast-2.amazonaws.com/images/slM1h1Gk8jaG.jpg','slM1h1Gk8jaG','<p></p><p>가성비 갑! 아크로 프로버즈 무선 이어폰으로</p><p>멋과 간편함 모두 챙기세요!</p><p></p><p></p>'),(1,2,'2024-02-16 01:37:40.293874',31,'2024-02-16 01:37:40.318124','마라탕밀키트❤','https://pasila.s3.ap-northeast-2.amazonaws.com/images/slM7CTjo8jaG.jpg','slM7CTjo8jaG','<p></p><p></p><p></p><p></p><p></p><p></p><p></p><p>마라 입문자도 충분히 드실 수 있게 만들었습니다. 시중에 판매되는 마라탕 기름에서 대부분의 기름을 뺐고 다진 마늘을 추가했습니다. 때문에 마라를 먹고 속쓰리고 배아파 화장실에서 고생하실 일 없게끔 만들어봤습니다.</p><p>(개개인에 따라 다르겠지만, 테스트 중에 처음엔 배앓이 하셨던 분들이 완성품에선 대부분 배앓이를 하지 않으셨어요. 100% 모두 다 배 안아플거라고 말씀을 못드리지만, 배 안아프도록 최대한 노력해보았어요)</p><p></p><p></p><p>밀키트 출시 직전까지도 고민을 많이 했는데요. 정말 냉정했던 많은 시식체험단 분들께서 완성품에 흡족해하시고 극찬해주셔서 출시를 결정하게 되었습니다. </p><p></p><p>많은 판매량 보다도 누군가에게 최고의 마라전골 마라탕이 되길 바라는 마음으로 준비했습니다. 믿고 주문해주신 분들께 큰 만족감과 행복이 전해졌으면 좋겠습니다.</p><p></p><p>여기서 더 말하면.. 사연팔이가 되므로 이만 줄이겠습니다. 여러분 정말 감사드리고 너무너무 고맙습니다. 진심으로 감사합니다</p><p></p><p></p><p></p>'),(1,2,'2024-02-16 02:03:34.642225',31,'2024-02-16 02:03:34.661506','약과','https://pasila.s3.ap-northeast-2.amazonaws.com/images/uWGAWfne2YK1.jpg','uWGAWfne2YK1','<p></p><p></p><p></p>'),(1,2,'2024-02-16 02:57:11.631350',31,'2024-02-16 02:57:11.654849','약과','https://pasila.s3.ap-northeast-2.amazonaws.com/images/YTL8vsYpETxC.jpg','YTL8vsYpETxC','<p></p><p></p><p></p>');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_option`
--

DROP TABLE IF EXISTS `product_option`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_option` (
  `discount_price` int DEFAULT NULL,
  `price` int DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `id` bigint NOT NULL,
  `version` bigint NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `product_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn4hmm6ex1vgn60c6uiqte400f` (`product_id`),
  CONSTRAINT `FKn4hmm6ex1vgn60c6uiqte400f` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_option`
--

LOCK TABLES `product_option` WRITE;
/*!40000 ALTER TABLE `product_option` DISABLE KEYS */;
INSERT INTO `product_option` VALUES (1590000,2000000,99,1,1,'기본','11FD88R5Y5XM'),(28000,30000,100,2,0,'립 세럼 로지','12FD88R5Y5XM'),(28000,30000,100,3,0,'립 세럼 베어','12FD88R5Y5XM'),(72800,112000,200,4,0,'블랙티 앰플 50ml 세트 2개','13FD88R5Y5XM'),(10000,12000,50,5,0,'전복죽 밀키트','21FD88R5Y5XM'),(27900,90000,50,6,0,'가정용참외 혼합과 2kg','22FD88R5Y5XM'),(55900,90000,50,7,0,'가정용참외 혼합과 5kg','22FD88R5Y5XM'),(10000,15000,49,8,1,'야채볶음밥 누룽지','23FD88R5Y5XM'),(19800,58000,10,9,0,'S','31FD88R5Y5XM'),(19800,58000,10,10,0,'M','31FD88R5Y5XM'),(19800,58000,10,11,0,'L','31FD88R5Y5XM'),(32200,55800,10,12,0,'화이트','32FD88R5Y5XM'),(32200,55800,10,13,0,'핑크','32FD88R5Y5XM'),(43800,56800,100,14,0,'베이지','33FD88R5Y5XM'),(43800,56800,100,15,0,'핑크','33FD88R5Y5XM'),(43800,56800,100,16,0,'블루','33FD88R5Y5XM'),(74000,150000,100,17,0,'라운드','41FD88R5Y5XM'),(74000,150000,100,18,0,'롱','41FD88R5Y5XM'),(74000,150000,100,19,0,'웨이브','41FD88R5Y5XM'),(23000,52000,100,20,0,'골드 부엉이','42FD88R5Y5XM'),(41000,52000,100,21,0,'부엉이 문종','42FD88R5Y5XM'),(29800,59600,100,61,0,'one size','slM1h1Gk8jaG'),(50660,59600,0,62,3,'one','kIHqpHc3KT9M'),(27000,30000,1157,63,2,'포도','LC6n6E5KpHOn'),(14100,15000,20,64,0,'약과','QxbTJ4J4A48c'),(14250,15000,20,65,0,'마라탕밀키트매운맛','slM7CTjo8jaG'),(14250,15000,10,66,0,'마라탕밀키트중간맛','slM7CTjo8jaG'),(14250,15000,20,67,0,'약과','h7keT7CiBvQ8'),(14250,15000,20,68,0,'못난이약과','uWGAWfne2YK1'),(14250,15000,20,69,0,'마라탕밀키트매운맛','pSgqvWqKklVe'),(14250,15000,20,70,0,'마라탕밀키트중간맛','pSgqvWqKklVe'),(13500,15000,5,71,0,'몬난이약과','YTL8vsYpETxC'),(23750,25000,2,72,0,'핑크','OkxcMbQrJLhN'),(22500,25000,5,73,0,'옐로우','OkxcMbQrJLhN'),(432000,480000,30,74,0,'보라카이패키지1','RDqO6GI7eNhr'),(522000,580000,20,75,0,'보라카이패키지2','RDqO6GI7eNhr'),(13950,15000,19,76,1,'로제마라','8inOTPiHR0I7'),(9500,10000,100,77,0,'콜라겐 밀크 스티커','AoZd7wwEs1ig'),(9500,10000,100,78,0,'수분 가득 스티커','AoZd7wwEs1ig'),(23750,25000,10,79,0,'M','3wtlN3txWpPT'),(23750,25000,20,80,0,'회색맨투맨','Iec9MRx9vBlc');
/*!40000 ALTER TABLE `product_option` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_option_seq`
--

DROP TABLE IF EXISTS `product_option_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_option_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_option_seq`
--

LOCK TABLES `product_option_seq` WRITE;
/*!40000 ALTER TABLE `product_option_seq` DISABLE KEYS */;
INSERT INTO `product_option_seq` VALUES (81);
/*!40000 ALTER TABLE `product_option_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shortping`
--

DROP TABLE IF EXISTS `shortping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shortping` (
  `is_active` tinyint DEFAULT '1',
  `like_cnt` int DEFAULT '0',
  `created_at` datetime(6) DEFAULT NULL,
  `title` varchar(50) NOT NULL,
  `video_url` varchar(2083) DEFAULT NULL,
  `id` varchar(15) NOT NULL,
  `product_id` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_roex2ws60wd418qdcg51r9i8g` (`product_id`),
  CONSTRAINT `FK69763w5di2ilem72noxdqpqr8` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shortping`
--

LOCK TABLES `shortping` WRITE;
/*!40000 ALTER TABLE `shortping` DISABLE KEYS */;
INSERT INTO `shortping` VALUES (1,0,'2024-02-16 03:26:59.453156','[이니스프리]10,000판달성!수분촉촉제품','https://i10a402.p.ssafy.io/download/shortping/13ABCR5.mp4','4pmz0mp8Op','13FD88R5Y5XM'),(1,0,'2024-02-16 09:54:06.867735','바지 진짜 예쁨 걍 미쳤음','https://i10a402.p.ssafy.io/download/shortping/31ABCR5.mp4','bYdIhzQuZ5','31FD88R5Y5XM'),(1,0,'2024-02-16 03:32:49.050879','핫해서 주문 폭주! 할인받아가세요','https://i10a402.p.ssafy.io/download/shortping/QxbTwBuwAc.mp4','gz0a8wP4Hf','OkxcMbQrJLhN'),(1,0,'2024-02-16 10:20:31.616553','','https://i10a402.p.ssafy.io/download/shortping/3wtloRgaWT.mp4','LC63DJDFp6','AoZd7wwEs1ig');
/*!40000 ALTER TABLE `shortping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-16 10:31:40
