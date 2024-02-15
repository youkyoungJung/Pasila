#Category
INSERT INTO `category`
VALUES (1, '뷰티', 'beauty_image_url'),
        (2, '음식', 'food_image_url'),
        (3, '패션', 'fashion_image_url'),
        (4, '라이프', 'life_image_url'),
        (5, '여행/체험', 'travel_experience_image_url'),
        (6, '테크', 'tech_image_url'),
        (7, '유아', 'kids_image_url');
        (8, '레저', 'activity_image_url');
        (9, '티켓', 'ticket_image_url');
        (10, '기타', 'etc_image_url');

#Member
INSERT INTO `member` VALUES
        ('2000-12-11',NOW(),NULL,1,'우리','김지우','김지우','01022222222','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','김지우의 김지이우우채널입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test1@test.com','profile-url','F','email'),
        ('2000-06-12',NOW(),NULL,2,'신한','최지우','최지우','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','최지우의 최지이우우채널입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test2@test.com','profile-url','F','email'),
        ('2000-02-13',NOW(),NULL,3,'신한','유소연','유소연','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test3@test.com','profile-url','F','email'),
        ('2000-01-04',NOW(),NULL,4,'신한','김소연','김소연','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다..','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test4@test.com','profile-url','F','email'),
        ('2000-02-15',NOW(),NULL,5,'신한','최동헌','최동헌','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test5@test.com','profile-url','M','email'),
        ('2000-01-16',NOW(),NULL,6,'신한','유동헌','유동헌','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test6@test.com','profile-url','M','email'),
        ('2000-04-17',NOW(),NULL,7,'신한','최경향','최경향','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test7@test.com','profile-url','F','email'),
        ('2000-03-18',NOW(),NULL,8,'신한','전경향','전경향','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test8@test.com','profile-url','F','email'),
        ('2000-02-19',NOW(),NULL,9,'신한','정유경','정유경','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$aYgVwG3dX9IqwSmKywlUCeJNZqG/tGevbDTcuoyt5FTab/fxTrxke',NULL,'test9@test.com','profile-url','F','email'),
        ('2000-05-10',NOW(),NULL,10,'신한','박지원','박지원','01011111111','o3fm7/jS96pkDPa1Wy3R/w==','서울시 강남구 역삼동','멀티캠퍼스','채널설명입니다.','$10$1LyG1oGjKeggHTX3KOtD.eLRKYUdTvgoJzTWrbhkj35CnQX..juYK',NULL,'wldnjs123@gmail.com','https://pasila.s3.ap-northeast-2.amazonaws.com/images/12.jpg','F','email');

#Product
INSERT INTO `product`(id, category_id, member_id, name, thumbnail, description, is_active, created_at, updated_at)
VALUES
('11FD88R5Y5XM', 1, 1, '리쥬란 홈케어 뷰티 디바이스 리쥬리프', 'https://shop-phinf.pstatic.net/20230720_6/1689817112095ceQeo_JPEG/19229125731850418_1728260416.jpg?type=o1000', '홈케어의 한계를 잊게 만드는 진짜 뷰티 디바이스.
 탄력 루틴으로 완성하는 얼굴라인 4주만에 나타나는 콜라겐 리모델링 효과! 하루 20분 초음파 고주파 카트리지로 처진 피부 더블업 케어!
 사용법: 1. 타겟 초음파 카트리지 장착 후 부스팅 젤을 도포해주세요. 2. 카트리지를 피부에 밀착하여 케어합니다.', 1, NOW(), NOW()),
('12FD88R5Y5XM', 1, 2, '프리메라 레티놀 볼륨 립 세럼 12g', 'https://shop-phinf.pstatic.net/20240129_145/1706518604447vlh7F_JPEG/33821288328759541_1278530703.jpg?type=o1000'
'최초 레티놀 립 세럼 리얼 볼륨 오버립. 사용할수록 탄력있게 차오르는 볼륨감으로 도톰하고 탱글한 입술! 사용 직후에도 자연스럽고 시원한 플럼핑 룩. 고밀도 젤의 높은 밀착력으로 윤기나는 입술까지!', 1, NOW(), NOW()),
('13FD88R5Y5XM', 1, 3, '이니스프리 블랙티 유스 인핸싱 앰플 50mL 대용량 단품 세트 2개', 'https://shop-phinf.pstatic.net/20240108_48/1704640507827pWrhb_JPEG/31943191712481902_2058059907.jpg?type=o1000'
'블랙티 유스 인핸싱 앰플. 복합적인 피부 고민을 단 한병으로 케어! 제주 블랙티에서 추출한 reset concentrate를 함유해 강력한 피부 컨디션 케어. 숫자로 증명하는 진짜 안티에이징! 한번만 써봐도 다음날 톤, 윤기, 결이 달라지는게 느껴져요.', 1, NOW(), NOW()),
('21FD88R5Y5XM', 2, 4, '기장끝집 오직 전복죽 밀키트 전복내장 간편 영양죽', 'https://shop-phinf.pstatic.net/20230224_191/1677227623607TrGQP_PNG/78363522290870889_859664421.png?type=o1000'
'누적 판매 1,000,000팩 이상 누적 후기 19,000건 이상! 오직 전복죽만 판매하여 전복죽 맛집으로 인정받았습니다. 전복과 전복내장 손질부터 전복죽 밀키트 생산과 냉동 과정까지도 전복죽의 모든 과정에서 확실한 차이를 만듭니다.', 1, NOW(), NOW()),
('22FD88R5Y5XM', 2, 5, '고당도 제철 성주 꿀참외 주먹참외 용암참외', ''
'', 1, NOW(), NOW()),
('23FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('31FD88R5Y5XM', 3, 1, '딥블랙 / 슬림 스트레이트 블랙 스판 데님 팬츠', 'https://shop-phinf.pstatic.net/20240214_32/1707878907640g8OMu_JPEG/109014742448951177_267551439.jpg?type=o1000', '- 컬러 : 블랙 - 소재 : 면97%, 스판3%
-특징 : 딥한 블랙컬러로 착용시 슬림해보이는 깔끔하고 부드러운 감촉이 돋보이는 블랙 팬츠. 밴딩이 드러나지 않는 히든허리밴딩 디자인. 아주 편하며 다리 전체도 스판성이 뛰어나 편안합니다. 밑위길이도 낙낙하여 허리 들뜸없이 핏됩니다.
-사이즈 : S,M,L', 1, NOW(), NOW()),
('32FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('33FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('41FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('42FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('43FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('51FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('52FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW()),
('53FD88R5Y5XM', 2, 4, '', ''
'', 1, NOW(), NOW());

#PRODUCT_OPTION
INSERT INTO `product_option`(id, product_id, name, stock, price, discount_price, version) VALUES
        (1, '11FD88R5Y5XM', '기본', 100, 2000000, 1590000, 0)
        (2, '12FD88R5Y5XM', '립 세럼 로지', 100, 30000, 28000, 0)
        (3, '12FD88R5Y5XM', '립 세럼 베어', 100, 30000, 28000, 0)
        (4, '13FD88R5Y5XM', '블랙티 앰플 50ml 세트 2개', 200, 112000, 72800, 0)
        (5, '21FD88R5Y5XM', '전복죽 밀키트', 50, 12000, 10000, 0)
        (1, '31FD88R5Y5XM', 'S', 10, 58000, 19800, 0),
        (2, '31FD88R5Y5XM', 'M', 10, 58000, 19800, 0),
        (3, '31FD88R5Y5XM', 'L', 10, 58000, 19800, 0),


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