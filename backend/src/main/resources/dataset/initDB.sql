#Category
INSERT INTO `category`
VALUES (1, '뷰티', 'beauty_image_url'),
        (2, '푸드', 'food_image_url'),
        (3, '패션', 'fashion_image_url'),
        (4, '라이프', 'life_image_url'),
        (5, '여행/체험', 'travel_experience_image_url'),
        (6, '키즈', 'kids_image_url'),
        (7, '테크', 'tech_image_url');

#Member
INSERT INTO `member` VALUES
        ('2000-12-11',NOW(),NULL,1,'우리','김지우','김지우','01022222222','bank-account','서울시 강남구 역삼동','멀티캠퍼스','김지우의 김지이우우채널입니다.','password-example',NULL,'abc@abc.com','profile-url','F','kakao'),
        ('2000-06-12',NOW(),NULL,2,'신한','최지우','최지우','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','최지우의 최지이우우채널입니다.','password-example',NULL,'cva@abc.com','profile-url','F','kakao'),
        ('2000-02-13',NOW(),NULL,3,'신한','유소연','유소연','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','F','kakao'),
        ('2000-01-04',NOW(),NULL,4,'신한','김소연','김소연','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다..','password-example',NULL,'cva@abc.com','profile-url','F','kakao'),
        ('2000-02-15',NOW(),NULL,5,'신한','최동헌','최동헌','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','M','kakao'),
        ('2000-01-16',NOW(),NULL,6,'신한','유동헌','유동헌','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','M','kakao'),
        ('2000-04-17',NOW(),NULL,7,'신한','최경향','최경향','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','F','kakao'),
        ('2000-03-18',NOW(),NULL,8,'신한','전경향','전경향','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','F','kakao'),
        ('2000-02-19',NOW(),NULL,9,'신한','정유경','정유경','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','F','kakao'),
        ('2000-05-10',NOW(),NULL,10,'신한','박지원','박지원','01011111111','bank-account','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','password-example',NULL,'cva@abc.com','profile-url','F','kakao');

#Product
INSERT INTO `product`
VALUES (1, 1, NOW(), 1, NULL, '파운데이션', 'product_thumnail_url-example', '파운데이션 팝니다. 14호, 21호, 23호, 24호 까지!!', '20FD88R7Y5XM'),
       (1, 3, NOW(), 1, NULL, '니트', 'product_thumnail_url-example', '니트 팝니다. S, M, L, XL 까지 있어요', '30FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '청바지', 'product_thumnail_url-example', '청바지 팝니다. S, M, L, XL 까지 있어요', '40FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '카디건', 'product_thumnail_url-example', '카디건 팝니다. S, M, L, XL 까지 있어요', '50FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '구스다운', 'product_thumnail_url-example', '구스다운 팝니다. S, M, L, XL 까지 있어요', '60FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '후리스', 'product_thumnail_url-example', '후리스 팝니다. S, M, L, XL 까지 있어요', '70FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '코트', 'product_thumnail_url-example', '코트 팝니다. S, M, L, XL 까지 있어요', '80FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '맨투맨', 'product_thumnail_url-example', '맨투맨 팝니다. S, M, L, XL 까지 있어요', '90FD88R5Y5XM'),
       (1, 3, NOW(), 1, NULL, '추리닝', 'product_thumnail_url-example', '추리닝 팝니다.S, M, L, XL 까지 있어요', '00FD88R5Y5XM');

#PRODUCT_OPTION
INSERT INTO `product_option` VALUES
        (1000, 10000, 3, 1, 0, '파운데이션 14호', '20FD88R7Y5XM'),
        (1000, 10000, 1, 2, 0, '파운데이션 21호', '20FD88R7Y5XM'),
        (1000, 10000, 2, 3, 0, '파운데이션 22호', '20FD88R7Y5XM'),
        (1000, 10000, 5, 4, 0, '파운데이션 23호', '20FD88R7Y5XM'),
        (1000, 10000, 3, 5, 0, '파운데이션 24호', '20FD88R7Y5XM'),
        (3000, 10000, 4, 6, 0, '핑크빛 앙고라 니트', '30FD88R5Y5XM'),
        (3000, 10000, 4, 7, 0, '정렬적인 앙고라 니트', '30FD88R5Y5XM'),
        (3000, 10000, 5, 8, 0, '청바지', '40FD88R5Y5XM'),
        (3000, 10000, 5, 9, 0, '카디건', '50FD88R5Y5XM'),
        (3000, 10000, 5, 10, 0, '구스다운', '60FD88R5Y5XM'),
        (3000, 10000, 5, 11, 0, '후리스', '70FD88R5Y5XM'),
        (3000, 10000, 5, 12, 0, '코트', '80FD88R5Y5XM'),
        (3000, 10000, 5, 13, 0, '맨투맨', '90FD88R5Y5XM'),
        (3000, 10000, 5, 14, 0, '추리닝', '00FD88R5Y5XM');


#Live
INSERT INTO `live` VALUES
         (1, 0, 20, NOW(), NULL, NULL, '2024-02-03', 1, '니트팝니다', 'full_video_url_exam', 1, '30FD88R5Y5XM', 'AI대본exam'),
         (1, 0, 20, NOW(), NULL, NULL, '2024-02-04', 1, '구스다운팝니다', 'full_video_url_exam', 2, '60FD88R5Y5XM', 'AI대본exam'),
         (1, 0, 20, NOW(), NULL, NULL, '2024-02-05', 1, '후리스팝니다', 'full_video_url_exam', 3, '70FD88R5Y5XM', 'AI대본exam'),
         (1, 0, 20, NOW(), NULL, NULL, '2024-02-06', 1, '코트팝니다', 'full_video_url_exam', 4, '80FD88R5Y5XM', 'AI대본exam'),
         (1, 0, 20, NOW(), NULL, NULL, '2024-02-07', 1, '맨투맨팝니다', 'full_video_url_exam', 5, '90FD88R5Y5XM', 'AI대본exam');


#Shortpring
INSERT INTO `shortping` VALUES
        (1, 20, NOW(), '폭삭한 앙고라 니트', 'short_video_url', 1, '30FD88R5Y5XM');