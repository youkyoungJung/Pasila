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
('22FD88R5Y5XM', 2, 5, '고당도 제철 성주 꿀참외 주먹참외 용암참외', 'https://shop-phinf.pstatic.net/20230215_200/1676455193892Xjyx3_JPEG/77591089614524128_1527759179.jpg?type=o1000'
'금메달 농산물 성주 참외입니다. 농장에서 당일수확 당일발송! 금메달농산물 봄과 여름하면 생각나는 과일은 참외!! 저희 금메달 농산물의 자랑거리인 제철과일 성주 참외에 대해 아시나요? 당도가 매우 우수한 성주참외입니다. 참외는 성주참외가 대중적으로 널리 알려져 있습니다. 그 중에서도 성주참외는 브랜드인만큼 다른 일반 성주참외보다 월등히 우수한맛이라고 여러분들께 자신있게 말할 수 있습니다.', 1, NOW(), NOW()),
('23FD88R5Y5XM', 2, 6, '콩고미 식물성 단백질 바삭바삭 고소한 야채볶음밥 누릉지', 'https://shop-phinf.pstatic.net/20230914_157/1694667077197UAT74_JPEG/2014807073929737_1772424659.JPG?type=o1000'
'맛있고 고소하고 바삭한맛 다양한 야채가 듬뿍 야채볶음밥 누릉지. 살찔까봐 먹지도 못한 누룽지 이젠 마음껏 드세요! 감칠맛이 뛰어난 야채볶음밥 누룽지. 고단백, 저탄수화물, 저지방, 저칼로리', 1, NOW(), NOW()),
('31FD88R5Y5XM', 3, 7, '딥블랙 / 슬림 스트레이트 블랙 스판 데님 팬츠', 'https://shop-phinf.pstatic.net/20240214_32/1707878907640g8OMu_JPEG/109014742448951177_267551439.jpg?type=o1000', '- 컬러 : 블랙 - 소재 : 면97%, 스판3%
-특징 : 딥한 블랙컬러로 착용시 슬림해보이는 깔끔하고 부드러운 감촉이 돋보이는 블랙 팬츠. 밴딩이 드러나지 않는 히든허리밴딩 디자인. 아주 편하며 다리 전체도 스판성이 뛰어나 편안합니다. 밑위길이도 낙낙하여 허리 들뜸없이 핏됩니다.
-사이즈 : S,M,L', 1, NOW(), NOW()),
('32FD88R5Y5XM', 3, 7, '애니베어 레터링 프린팅 루즈핏 롱 봄 카라셔츠 (2color)', 'https://shop-phinf.pstatic.net/20240208_114/1707321250204axg1D_JPEG/108457085026479901_114507757.jpg?type=o1000'
'귀엽고 특이한 디자인으로 눈에 확~띄는 곰돌이 프린팅 언발 기장 롱 셔츠 보여드릴게요! 부드럽고 하늘하늘거리는 소재라 피부에 챠르르 닿는 감촉이 좋고 구김이 덜해 휘뚜루마뚜루 활용하기 좋은 아이템이랍니다! 전체적으로 여유있는 루즈핏에 롱한 기장감으로 언니들의 미운 군살들을 쏙! 여리여리한 핏으로 연출해주어용. Color: 화이트, 핑크  FABRIC: 폴리혼방  SIZE: free', 1, NOW(), NOW()),
('33FD88R5Y5XM', 3, 7, '콘드 홀가먼트 라운드넥 울 니트', 'https://shop-phinf.pstatic.net/20231117_155/1700225587078ajuFH_JPEG/27052852278162305_1703841395.jpg?type=o1000'
'피부에 닿는 부드러움이 너무 고급스러운 울 혼방소재의 니트로 울의 포근함과 따뜻함이 느껴지는 제품이에요. 봉제선 없이 통으로 편직되는 홀가먼트 가공법으로 피부가 예민하신 분들에게 더욱 좋은 하이퀄리티 니트입니다. ㅌ두껍지 않은 슬림한 원단으로 부해 보이지 않고 깔끔하게 떨어지는 스타일이라 아우터 안에 가볍게 입기 좋습니다. 사이즈 free 울80% 나일론20% 색상: 핑크, 베이지, 블루', 1, NOW(), NOW()),
('41FD88R5Y5XM', 4, 4, '네르하 이케바나 화병 이케바나롱 대형 유리 화병 꽃병', 'https://shop-phinf.pstatic.net/20220329_98/1648520298936YVwrt_JPEG/49656133511298966_1211701576.jpg?type=o1000'
'유명 브랜드 제품도 식물, 물에 의한 변색이 나타나고 저가형 중국산 황동 도금 이케바나 화병에서는 빈번하게 부식과 변색이 발생합니다. 그래서 직접 만들었습니다. 100% 스테인리스 국내 제작 변색, 부식 없는 이케바나 화병! 취향에 따라 더 다양하게 선택할 수 있도록 라운드, 롱, 웨이브 옵션이 있습니다. 고급 중대형 이케바나 입니다. 선물용으로 좋습니다. 특별한 선물, 집들이 선물로 추천합니다.', 1, NOW(), NOW()),
('42FD88R5Y5XM', 4, 4, '골드 부엉이장식품 금부엉이 집들이 집들이선물 인테리어 풍수 소품', 'https://shop-phinf.pstatic.net/20200901_85/1598890222080UNsqa_JPEG/36251764720864531_1427925739.JPG?type=o1000'
'골드 부엉이 장식품과 부엉이 문종은 선물 포장이 가능합니다. 어디에서나 볼 수 있는 흔한 포장이 아닌, 고급스러운 선물상자는 여기서만 볼 수 있죠. 소중한 마음과 예쁨을 듬뿍 담아 보내드립니다.', 1, NOW(), NOW()),
('43FD88R5Y5XM', 4, 4, '', ''
'', 1, NOW(), NOW());

#PRODUCT_OPTION
INSERT INTO `product_option`(id, product_id, name, stock, price, discount_price, version) VALUES
        (1, '11FD88R5Y5XM', '기본', 100, 2000000, 1590000, 0)
        (2, '12FD88R5Y5XM', '립 세럼 로지', 100, 30000, 28000, 0)
        (3, '12FD88R5Y5XM', '립 세럼 베어', 100, 30000, 28000, 0)
        (4, '13FD88R5Y5XM', '블랙티 앰플 50ml 세트 2개', 200, 112000, 72800, 0)
        (5, '21FD88R5Y5XM', '전복죽 밀키트', 50, 12000, 10000, 0)
        (6, '22FD88R5Y5XM', '가정용참외 혼합과 2kg', 50, 90000, 27900, 0)
        (7, '22FD88R5Y5XM', '가정용참외 혼합과 5kg', 50, 90000, 55900, 0)
        (8, '23FD88R5Y5XM', '야채볶음밥 누룽지', 50, 15000, 10000, 0)
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