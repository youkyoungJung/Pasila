#Category
INSERT INTO `category`
VALUES (1, '뷰티', 'beauty_image_url'),
        (2, '음식', 'food_image_url'),
        (3, '패션', 'fashion_image_url'),
        (4, '라이프', 'life_image_url'),
        (5, '여행/체험', 'travel_experience_image_url'),
        (6, '테크', 'tech_image_url'),
        (7, '유아', 'kids_image_url'),
        (8, '레저', 'activity_image_url'),
        (9, '티켓', 'ticket_image_url');

#Member
INSERT INTO `member`(birth, created_at, deleted_at, id, bank, channel, name, phone,address, address_detail,account,description,password,token,email,profile,gender,login_type) VALUES
        ('2001-12-11','2017-12-12 03:47:46',NULL,1,'우리','ROSE','김로제','01043728973','경기 용인시 처인구 포곡읍 에버랜드로 199','에버랜드','EcvVMFnTsLgGCGoKhms8bw==','김로제의 김로오오제채널입니다.','$2a$10$oyMCmb6bNyr5VhTOgjnZP.8G66o6c9PuUQ1Vo3SLM4q08NN2VI5nW',NULL,'qlenfrl101@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/1.jpg','F','email'),
        ('1994-06-12','2019-01-30 12:27:43',NULL,2,'신한','JENNIE','최제니','01023768332','서울 중구 세종대로 40','숭례문','tBmnp9aa95DZLfnIhdg9Ug==','최제니의 개인채널입니다.','$2a$10$LhQI1.0OWHmtqqx8dkaaHuHGrM688.J9lQxjFziEkTrY7A3fRrEsm',NULL,'wjsrudgid@naver.com ','https://pasila.s3.ap-northeast-2.amazonaws.com/member/2.jpg','F','email'),
        ('1992-02-13','2022-02-10 12:42:16',NULL,3,'국민','KARINA','유리나','01001478922','서울 중구 을지로 281','동대문디자인플라자','ipQgkBHiREcilk8MwpQkfQ==','안녕하세요 좋은 상품 신속한 배송으로 보답하겠습니다.','$2a$10$GAwi2A23CB8BoedRnwJAXu25rFoSKBj8OZJOBq2IPcudZwcWk8STK',NULL,'karina00@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/3.jpg','F','email'),
        ('1996-01-04','2022-04-07 09:27:22',NULL,4,'신한','감나라농원','김소연','01098736578','서울특별시 서초구 올림픽대로 2085-14','세빛섬','LBvkjfb0tfVM0iMfAMUGVg==','직접 수확한 사과를 판매하고 있습니다!!','$2a$10$MXF6zO1c6UGx/JpEbWOosOrpnXD2A47cFujohG2tho2xeavWg7ULi',NULL,'sy111@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/4.jpg','F','email'),
        ('1987-02-15','2023-01-06 10:19:12',NULL,5,'농협','이미테이션','최동헌','01019983201','서울특별시 용산구 서빙고로 137','국립중앙박물관','M+m9viyB8xXjZsnD5cSMZA==','가품일 경우 100배로 보장 해드립니다.','$2a$10$00PCkElTccOhzKkMqMIeru7Y9ka1wAXdvdiEbpgvTCM3cvJmWbJaC',NULL,'ehdgjs9989@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/5.jpg','M','email'),
        ('1988-01-16','2023-02-16 01:07:11',NULL,6,'기업','의류수거함','유동헌','01089333299','서울 종로구 삼청로 30','국립현대미술관 서울','8MRpwWAKcWNmxHVCgVC9HQ==','어디서도 보지 못한 디자인의 옷으로 승부합니다. ','$2a$10$gg0SIb0GB6DE5nCEpx.I6.hMBo/gFMcZH/OK.hN11h3EdsXq/78Mm',NULL,'dheon77@gmail.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/6.jpg','M','email'),
        ('1990-04-17','2023-11-11 11:22:18',NULL,7,'하나','오동나무','최경향','01030912890','서울 광진구 능동로 216','어린이대공원','i8zZJ6cl7PpyAbJPwA944w==','100프로 수제작 목공예품만 취급합니다.','$2a$10$5btkR8m8om2PMTg8j/QZ/uMcm12Osh7cA.bMT0gpf4j2oMX4kEzFG',NULL,'rud223@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/7.jpg','F','email'),
        ('1999-03-18','2023-12-25 12:25:12',NULL,8,'우리','치악산복숭아','전경향','01098347211','서울특별시 강남구 테헤란로7길 22','한국 과학기술회관','UygMRRXj/L6Dh0+JIQ3U4A==','치악산 복숭아 당도최고','$2a$10$C7axTSqP3lx8.JpKA1AD9.uR7knBDbQIdLY4o29rv8cWiDEYpsd4S',NULL,'rudgid123@gmail.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/8.jpg','F','email'),
        ('2001-02-19','2024-01-01 11:27:38',NULL,9,'국민','자동차신발','정유경','01032542253','서울 종로구 삼청로 30','국립민속박물관','y+kTf7JIcyrhF9ud3MoMtQ==','앗! 타이어 신발보다 싸다.','$2a$10$p/bLN7BzKNy6VWAOGPEssezMcNx8lFn0hWBvDwEKvUux4QtLOrJ02',NULL,'yk1212@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/9.jpg','F','email'),
        ('1997-05-10','2024-02-07 15:20:19',NULL,10,'국민','무우신사','박지원','01032124513','서울 서초구 반포대로 201','국립중앙도서관','5XUuaPS1fCMjuOxkffKTEg==','무진장 신발 사진이 많은곳','$2a$10$veFxowgyFMVj7ki0xJnMbOE03kyG/NWrLj1FkW0OL/bIoKc1oc3ES',NULL,'wldnjs3211@naver.com','https://pasila.s3.ap-northeast-2.amazonaws.com/member/10.jpg','F','email');

#Product
INSERT INTO `product`(id, category_id, member_id, name, thumbnail, description, is_active, created_at, updated_at)
VALUES
('11FD88R5Y5XM', 1, 1, '리쥬란 홈케어 뷰티 디바이스 리쥬리프', 'https://shop-phinf.pstatic.net/20230720_6/1689817112095ceQeo_JPEG/19229125731850418_1728260416.jpg?type=o1000', '홈케어의 한계를 잊게 만드는 진짜 뷰티 디바이스.
 탄력 루틴으로 완성하는 얼굴라인 4주만에 나타나는 콜라겐 리모델링 효과! 하루 20분 초음파 고주파 카트리지로 처진 피부 더블업 케어!
 사용법: 1. 타겟 초음파 카트리지 장착 후 부스팅 젤을 도포해주세요. 2. 카트리지를 피부에 밀착하여 케어합니다.', 1, NOW(), NOW()),
('12FD88R5Y5XM', 1, 2, '프리메라 레티놀 볼륨 립 세럼 12g', 'https://shop-phinf.pstatic.net/20240129_145/1706518604447vlh7F_JPEG/33821288328759541_1278530703.jpg?type=o1000',
 '최초 레티놀 립 세럼 리얼 볼륨 오버립. 사용할수록 탄력있게 차오르는 볼륨감으로 도톰하고 탱글한 입술! 사용 직후에도 자연스럽고 시원한 플럼핑 룩. 고밀도 젤의 높은 밀착력으로 윤기나는 입술까지!', 1, NOW(), NOW()),
('13FD88R5Y5XM', 1, 1, '이니스프리 블랙티 유스 인핸싱 앰플', 'https://shop-phinf.pstatic.net/20240108_48/1704640507827pWrhb_JPEG/31943191712481902_2058059907.jpg?type=o1000',
 '블랙티 유스 인핸싱 앰플. 복합적인 피부 고민을 단 한병으로 케어! 제주 블랙티에서 추출한 reset concentrate를 함유해 강력한 피부 컨디션 케어. 숫자로 증명하는 진짜 안티에이징! 한번만 써봐도 다음날 톤, 윤기, 결이 달라지는게 느껴져요.', 1, NOW(), NOW()),
('21FD88R5Y5XM', 2, 3, '기장끝집 오직 전복죽 밀키트 전복내장 간편 영양죽', 'https://shop-phinf.pstatic.net/20230224_191/1677227623607TrGQP_PNG/78363522290870889_859664421.png?type=o1000',
 '누적 판매 1,000,000팩 이상 누적 후기 19,000건 이상! 오직 전복죽만 판매하여 전복죽 맛집으로 인정받았습니다. 전복과 전복내장 손질부터 전복죽 밀키트 생산과 냉동 과정까지도 전복죽의 모든 과정에서 확실한 차이를 만듭니다.', 1, NOW(), NOW()),
('22FD88R5Y5XM', 2, 8, '고당도 제철 성주 꿀참외 주먹참외 용암참외', 'https://shop-phinf.pstatic.net/20230215_200/1676455193892Xjyx3_JPEG/77591089614524128_1527759179.jpg?type=o1000',
 '금메달 농산물 성주 참외입니다. 농장에서 당일수확 당일발송! 금메달농산물 봄과 여름하면 생각나는 과일은 참외!! 저희 금메달 농산물의 자랑거리인 제철과일 성주 참외에 대해 아시나요? 당도가 매우 우수한 성주참외입니다. 참외는 성주참외가 대중적으로 널리 알려져 있습니다. 그 중에서도 성주참외는 브랜드인만큼 다른 일반 성주참외보다 월등히 우수한맛이라고 여러분들께 자신있게 말할 수 있습니다.', 1, NOW(), NOW()),
('23FD88R5Y5XM', 2, 3, '콩고미 식물성 단백질 바삭바삭 고소한 야채볶음밥 누릉지', 'https://shop-phinf.pstatic.net/20230914_157/1694667077197UAT74_JPEG/2014807073929737_1772424659.JPG?type=o1000',
 '맛있고 고소하고 바삭한맛 다양한 야채가 듬뿍 야채볶음밥 누릉지. 살찔까봐 먹지도 못한 누룽지 이젠 마음껏 드세요! 감칠맛이 뛰어난 야채볶음밥 누룽지. 고단백, 저탄수화물, 저지방, 저칼로리', 1, NOW(), NOW()),
('31FD88R5Y5XM', 3, 6, '딥블랙 / 슬림 스트레이트 블랙 스판 데님 팬츠', 'https://shop-phinf.pstatic.net/20240214_32/1707878907640g8OMu_JPEG/109014742448951177_267551439.jpg?type=o1000', '- 컬러 : 블랙 - 소재 : 면97%, 스판3%
-특징 : 딥한 블랙컬러로 착용시 슬림해보이는 깔끔하고 부드러운 감촉이 돋보이는 블랙 팬츠. 밴딩이 드러나지 않는 히든허리밴딩 디자인. 아주 편하며 다리 전체도 스판성이 뛰어나 편안합니다. 밑위길이도 낙낙하여 허리 들뜸없이 핏됩니다.
-사이즈 : S,M,L', 1, NOW(), NOW()),
('32FD88R5Y5XM', 3, 1, '애니베어 레터링 프린팅 루즈핏 롱 봄 카라셔츠', 'https://shop-phinf.pstatic.net/20240208_114/1707321250204axg1D_JPEG/108457085026479901_114507757.jpg?type=o1000',
 '귀엽고 특이한 디자인으로 눈에 확~띄는 곰돌이 프린팅 언발 기장 롱 셔츠 보여드릴게요! 부드럽고 하늘하늘거리는 소재라 피부에 챠르르 닿는 감촉이 좋고 구김이 덜해 휘뚜루마뚜루 활용하기 좋은 아이템이랍니다! 전체적으로 여유있는 루즈핏에 롱한 기장감으로 언니들의 미운 군살들을 쏙! 여리여리한 핏으로 연출해주어용. Color: 화이트, 핑크  FABRIC: 폴리혼방  SIZE: free', 1, NOW(), NOW()),
('33FD88R5Y5XM', 3, 2, '콘드 홀가먼트 라운드넥 울 니트', 'https://shop-phinf.pstatic.net/20231117_155/1700225587078ajuFH_JPEG/27052852278162305_1703841395.jpg?type=o1000',
 '피부에 닿는 부드러움이 너무 고급스러운 울 혼방소재의 니트로 울의 포근함과 따뜻함이 느껴지는 제품이에요. 봉제선 없이 통으로 편직되는 홀가먼트 가공법으로 피부가 예민하신 분들에게 더욱 좋은 하이퀄리티 니트입니다. 두껍지 않은 슬림한 원단으로 부해 보이지 않고 깔끔하게 떨어지는 스타일이라 아우터 안에 가볍게 입기 좋습니다. 사이즈 free 울80% 나일론20% 색상: 핑크, 베이지, 블루', 1, NOW(), NOW()),
('41FD88R5Y5XM', 4, 7, '네르하 이케바나 화병 이케바나롱 대형 유리 화병 꽃병', 'https://shop-phinf.pstatic.net/20220329_98/1648520298936YVwrt_JPEG/49656133511298966_1211701576.jpg?type=o1000',
 '유명 브랜드 제품도 식물, 물에 의한 변색이 나타나고 저가형 중국산 황동 도금 이케바나 화병에서는 빈번하게 부식과 변색이 발생합니다. 그래서 직접 만들었습니다. 100% 스테인리스 국내 제작 변색, 부식 없는 이케바나 화병! 취향에 따라 더 다양하게 선택할 수 있도록 라운드, 롱, 웨이브 옵션이 있습니다. 고급 중대형 이케바나 입니다. 선물용으로 좋습니다. 특별한 선물, 집들이 선물로 추천합니다.', 1, NOW(), NOW()),
('42FD88R5Y5XM', 4, 7, '골드 부엉이장식품 인테리어 풍수 소품', 'https://shop-phinf.pstatic.net/20200901_85/1598890222080UNsqa_JPEG/36251764720864531_1427925739.JPG?type=o1000',
 '골드 부엉이 장식품과 부엉이 문종은 선물 포장이 가능합니다. 어디에서나 볼 수 있는 흔한 포장이 아닌, 고급스러운 선물상자는 여기서만 볼 수 있죠. 소중한 마음과 예쁨을 듬뿍 담아 보내드립니다. 실제로 보시면 반짝이는 큐빅스톤장식이 더욱 빛을 발합니다. 선물로 굉장히 인기가 많아요', 1, NOW(), NOW());


#PRODUCT_OPTION
INSERT INTO `product_option`(id, product_id, name, stock, price, discount_price, version) VALUES
        (1, '11FD88R5Y5XM', '기본', 100, 2000000, 1590000, 0),
        (2, '12FD88R5Y5XM', '립 세럼 로지', 100, 30000, 28000, 0),
        (3, '12FD88R5Y5XM', '립 세럼 베어', 100, 30000, 28000, 0),
        (4, '13FD88R5Y5XM', '블랙티 앰플 50ml 세트 2개', 200, 112000, 72800, 0),
        (5, '21FD88R5Y5XM', '전복죽 밀키트', 50, 12000, 10000, 0),
        (6, '22FD88R5Y5XM', '가정용참외 혼합과 2kg', 50, 90000, 27900, 0),
        (7, '22FD88R5Y5XM', '가정용참외 혼합과 5kg', 50, 90000, 55900, 0),
        (8, '23FD88R5Y5XM', '야채볶음밥 누룽지', 50, 15000, 10000, 0),
        (9, '31FD88R5Y5XM', 'S', 10, 58000, 19800, 0),
        (10, '31FD88R5Y5XM', 'M', 10, 58000, 19800, 0),
        (11, '31FD88R5Y5XM', 'L', 10, 58000, 19800, 0),
        (12, '32FD88R5Y5XM', '화이트', 10, 55800, 32200, 0),
        (13, '32FD88R5Y5XM', '핑크', 10, 55800, 32200, 0),
        (14, '33FD88R5Y5XM', '베이지', 100, 56800, 43800, 0),
        (15, '33FD88R5Y5XM', '핑크', 100, 56800, 43800, 0),
        (16, '33FD88R5Y5XM', '블루', 100, 56800, 43800, 0),
        (17, '41FD88R5Y5XM', '라운드', 100, 150000, 74000, 0),
        (18, '41FD88R5Y5XM', '롱', 100, 150000, 74000, 0),
        (19, '41FD88R5Y5XM', '웨이브', 100, 150000, 74000, 0),
        (20, '42FD88R5Y5XM', '골드 부엉이', 100, 52000, 23000, 0),
        (21, '42FD88R5Y5XM', '부엉이 문종', 100, 52000, 41000, 0);


#Live
INSERT INTO `live`(id, member_id, product_id, script, title, full_video_url, like_cnt, live_scheduled_at, is_active, created_at) VALUES
('11ABCR5', 1, '11FD88R5Y5XM', '스크립트', '리쥬리프X채우 발렌타인 특집 LIVE!', 'https://i10a402.p.ssafy.io/download/live/11ABCR5.mp4', 0, '2024-02-15 13:46:33', 1, '2024-02-13 13:46:33'),
('12ABCR5', 2, '12FD88R5Y5XM', '스크립트', '프리메라 24년 첫 브랜드데이 LIVE', 'https://i10a402.p.ssafy.io/download/live/12ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('13ABCR5', 1, '13FD88R5Y5XM', '스크립트', '[이니스프리x브랜드데이] 빅세일 특집 LIVE', 'https://i10a402.p.ssafy.io/download/live/13ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('21ABCR5', 3, '21FD88R5Y5XM', '스크립트', '기장끝집 선물세트 NEW 출시!🎁 전복죽_전복미역국', 'https://i10a402.p.ssafy.io/download/live/21ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('22ABCR5', 8, '22FD88R5Y5XM', '스크립트', '아삭하고 달콤한 성주 꿀 참외 무료배송', 'https://i10a402.p.ssafy.io/download/live/22ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('23ABCR5', 3, '23FD88R5Y5XM', '스크립트', '콩고미의 맛있는 현미&현미찹, 야채볶음밥 누룽지🥘', 'https://i10a402.p.ssafy.io/download/live/23ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('31ABCR5', 6, '31FD88R5Y5XM', '스크립트', '30%할인! 블랙진💚1+1 봄신상 데일리룩', 'https://i10a402.p.ssafy.io/download/live/31ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('32ABCR5', 1, '32FD88R5Y5XM', '스크립트', '아묻따 마인봄신상❤️ 라방특가 할인❤️', 'https://i10a402.p.ssafy.io/download/live/32ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('33ABCR5', 2, '33FD88R5Y5XM', '스크립트', 'FW클리어런스 행사 😀 마지막 SALE 🍀', 'https://i10a402.p.ssafy.io/download/live/33ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('41ABCR5', 7, '41FD88R5Y5XM', '스크립트', '❤️[10%할인+사은품]❤️네르하 이케바나 화병 라이브', 'https://i10a402.p.ssafy.io/download/live/41ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33'),
('42ABCR5', 7, '42FD88R5Y5XM', '스크립트', '예쁜 소품들 다 모였어요✨감성 인테리어 소품샵💜 ', 'https://i10a402.p.ssafy.io/download/live/42ABCR5.mp4', 0, '2024-02-15 13:07:22', 1, '2024-02-13 13:46:33');


#Shortpring
# INSERT INTO `shortping` VALUES
#         (1, 20, NOW(), '폭삭한 앙고라 니트', 'short_video_url', 1, '30FD88R5Y5XM');