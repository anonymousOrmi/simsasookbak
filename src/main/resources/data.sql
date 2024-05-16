-- Insert dummy data for Member
-- 1234
-- 추가 --
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('kimsanghyung@gmail.com', '김상형', 'USER', '1900-05-01', '$2a$12$GiLgp9UrAqlaVIxfbHq4peMVsmMcJOT78jmhee2MRH7S3w1p4lhfi', '일반', '010-1234-1234');
SET @member_id_kim = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('parkjieun@gmail.com', '박지은', 'BUSINESS', '1920-06-02', '$2a$12$ButAQozslP.KtYJWCnilWezIyN5rodbNrvQvrjEHk0IZ86E35JHge', '일반', '010-2345-1735');
SET @member_id_park = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('songchanhyuk@gmail.com', '송찬혁', 'ADMIN', '1950-02-03', '$2a$12$MeL9ZRf0LkL2buqGxhGoVenE6gwMgy1Aj3AciuKkvZs3BvTIvtD6K', '일반', '010-3716-5576');
SET @member_id_song = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('leeyoungseok@gmail.com', '이영석', 'USER', '1970-12-21', '$2a$12$Cl.T37Nntk8Q9Uzdgj2NRu/o930DKBshwIOTNBkyZQrhx4.bGuGK.', '일반', '010-3742-9985');
SET @member_id_lee = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('jeongminseok@gmail.com', '정민석', 'USER', '1985-12-21', '$2a$12$y7h/Jux3BV6mXrhmzRf33OxPm709Y/Fw62B0ZG/dyChpoVYHu5piS', '일반', '010-7324-5421');
SET @member_id_jeong = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('choibohyun@gmail.com', '최보현', 'BUSINESS', '2000-08-01', '$2a$12$qU9kKWpGjrbmgavQU4/VOeU3CHBIqgpEuDHo4JvvENKX5ZfhhPUWe', '일반', '010-6785-3452');
SET @member_id_choi = LAST_INSERT_ID();

--추가 --
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('yoojaeseok@gmail.com', '유재석', 'BUSINESS', '2000-08-01', '$2a$12$ecuVAeY60hU12hUKJqLw2.9ZQtZHXeBCyDmk9pT63lK7HP1Uf5pyC', '일반', '010-6785-3452');
SET @member_id_yu = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('jeonghyeongdon@gmail.com', '정형돈', 'USER', '2000-08-01', '$2a$12$fZxx/VotwmLdEbz/Csouiuk5APTwPl2HSf09Uo4C1rWk1vb5BtsqO', '일반', '010-6785-3452');
SET @member_id_yu = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('nohongcheol@gmail.com', '노홍철', 'BUSINESS', '2000-08-01', '$2a$12$PiBrP7ZaokzPNzETdDvNZOwmhAVFoAht16NQgBNTMmkP5bPUwAvkW', '일반', '010-6785-3452');
SET @member_id_no = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('parkmyeongsu@gmail.com', '박명수', 'BUSINESS', '2000-08-01', '$2a$12$5hRKU27PvUptfKBo0z5KUOkA/IxCo9AwTyD2fVnCIwMA0hMJYcP9G', '일반', '010-6785-3452');
SET @member_id_park2 = LAST_INSERT_ID();

INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('jeongjunha@gmail.com', '정준하', 'BUSINESS', '2000-08-01', '$2a$12$.WFOztLxY/K5ulKHFpPcp.sDJQk.s4sgT2zINkKkwTkDhyr16fV4.', '일반', '010-6785-3452');
SET @member_id_jung = LAST_INSERT_ID();

--member end

--
-- --accommodation start
--
-- 서울 지역 숙소 추가
INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_park, '파크 호텔', '서울의 중심지에 위치한 편리한 숙소', '서울', '서울 중구 남대문로 5가 123-456', '08:00:00', '12:00:00', false);
SET @accommodation_id_seoul_1 = LAST_INSERT_ID();

INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_park, '서울 타워 호텔', '남산의 아름다운 전망을 즐길 수 있는 호텔', '서울', '서울 용산구 남산공원길 105', '10:00:00', '15:00:00', false);
SET @accommodation_id_seoul_2 = LAST_INSERT_ID();

-- 청주 지역 숙소 추가
INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_park, '그린 호텔', '청주의 자연과 어우러진 휴식 공간', '청주', '청주시 상당구 상당로 1234', '09:00:00', '14:00:00', false);
SET @accommodation_id_cheongju_1 = LAST_INSERT_ID();

INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_park, '청주 펜션', '편안한 휴식을 위한 아늑한 펜션', '청주', '청주시 흥덕구 흥덕대로 5678', '11:00:00', '16:00:00', false);
SET @accommodation_id_cheongju_2 = LAST_INSERT_ID();

-- 대전 지역 숙소 추가
INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_choi, '초이스 호텔', '대전의 편안한 휴식을 제공하는 호텔', '대전', '대전시 서구 둔산로 101', '13:00:00', '18:00:00', false);
SET @accommodation_id_daejeon_1 = LAST_INSERT_ID();

INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_choi, '대전 게스트하우스', '편안한 휴식을 즐길 수 있는 게스트하우스', '대전', '대전시 유성구 대학로 1234', '12:00:00', '17:00:00', false);
SET @accommodation_id_daejeon_2 = LAST_INSERT_ID();

--accommodation end

--room start
-- 서울 숙소의 방 추가
INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_seoul_1, '서울 호텔 - 더블룸', 20000, '편안한 더블 침대가 있는 객실', '실내 금연');
SET @room_id_seoul_1_1 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_seoul_1, '서울 호텔 - 트윈룸', 20000, '넓은 트윈 침대가 있는 객실', '방 안에서 소음을 자제해주세요');
SET @room_id_seoul_1_2 = LAST_INSERT_ID();

-- 서울 타워 호텔의 방 추가
INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_seoul_2, '남산뷰 - 스위트룸', 30000, '남산의 아름다운 전망을 즐길 수 있는 고급스러운 객실', '바깥에서 들리는 소음에 주의해주세요');
SET @room_id_seoul_2_1 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_seoul_2, '시티뷰 - 스위트룸', 30000, '도심의 환상적인 뷰를 즐길 수 있는 고급 객실', '방 안에서 흡연은 금지됩니다');
SET @room_id_seoul_2_2 = LAST_INSERT_ID();

-- 청주 숙소의 방 추가
INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_cheongju_1, '그린 호텔 - 싱글룸', 20000, '아늑하고 편안한 싱글 침대가 있는 객실', '흡연은 객실 외부에서만 허용됩니다');
SET @room_id_cheongju_1_1 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_cheongju_1, '그린 호텔 - 더블룸', 20000, '편안한 더블 침대가 있는 넓은 객실', '방 내에서의 금연을 준수해주세요');
SET @room_id_cheongju_1_2 = LAST_INSERT_ID();

-- 청주 펜션의 방 추가
INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_cheongju_2, '청주 펜션 - 더블룸', 25000, '넓은 더블 침대가 있는 공간', '실내에서의 흡연은 엄격히 금지됩니다');
SET @room_id_cheongju_2_1 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_cheongju_2, '청주 펜션 - 트윈룸', 25000, '시원하고 편안한 트윈 침대가 있는 객실', '방 안에서 소음을 최소화해주세요');
SET @room_id_cheongju_2_2 = LAST_INSERT_ID();

-- 대전 숙소의 방 추가
INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_daejeon_1, '초이스 호텔 - 더블룸', 18000, '아늑하고 편안한 더블 침대가 있는 객실', '방 안에서의 흡연은 금지됩니다');
SET @room_id_daejeon_1_1 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_daejeon_1, '초이스 호텔 - 트윈룸', 18000, '넓고 시원한 트윈 침대가 있는 객실', '흡연은 객실 외부에서만 가능합니다');
SET @room_id_daejeon_1_2 = LAST_INSERT_ID();

-- 대전 게스트하우스의 방 추가
INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_daejeon_2, '대전 게스트하우스 - 싱글룸', 12000, '싱글 침대와 개인 욕실이 있는 객실', '방 안에서의 흡연은 금지됩니다');
SET @room_id_daejeon_2_1 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_daejeon_2, '대전 게스트하우스 - 더블룸', 12000, '편안한 더블 침대와 개인 욕실이 있는 객실', '흡연은 객실 외부에서만 가능합니다');
SET @room_id_daejeon_2_2 = LAST_INSERT_ID();

--room end

--
-- --reservation start
--
-- 새로운 예약 추가
-- 서울 숙소의 첫 번째 방에 대한 새로운 예약 추가
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_lee, @accommodation_id_seoul_1, @room_id_seoul_1_1, '대기', '2024-06-01', '2024-06-03', '조용한 방으로 예약합니다');

-- 서울 숙소의 두 번째 방에 대한 새로운 예약 추가
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_jeong, @accommodation_id_seoul_1, @room_id_seoul_1_2, '대기', '2024-06-10', '2024-06-12', '남산 뷰가 있는 방으로 예약합니다');

-- 청주 숙소의 첫 번째 방에 대한 새로운 예약 추가
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_jeong, @accommodation_id_cheongju_1, @room_id_cheongju_1_1, '대기', '2024-06-15', '2024-06-17', '객실 내 화장실 청소가 잘 되어있길 바랍니다');

-- 청주 숙소의 두 번째 방에 대한 새로운 예약 추가
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_park, @accommodation_id_cheongju_1, @room_id_cheongju_1_2, '대기', '2024-06-20', '2024-06-22', '두 번째 방 예약합니다');

INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_choi, @accommodation_id_cheongju_2, @room_id_cheongju_2_1, '대기', '2024-06-20', '2024-06-22', '두 번째 방 예약합니다');

-- 대전 숙소의 첫 번째 방에 대한 새로운 예약 추가
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_lee, @accommodation_id_daejeon_1, @room_id_daejeon_1_1, '대기', '2024-06-25', '2024-06-27', '더블 침대가 있는 방으로 변경합니다');

-- 대전 숙소의 두 번째 방에 대한 새로운 예약 추가
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_jeong, @accommodation_id_daejeon_2, @room_id_daejeon_2_2, '대기', '2024-06-30', '2024-07-02', '더블 침대가 있는 방으로 변경합니다');
--
-- --reservation end
--
-- --review start
--
-- 리뷰 INSERT문 수정
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_park, @accommodation_id_seoul_1, '침대가 좋아요', 5, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_park, @accommodation_id_seoul_1, '배게가 좋아요', 5, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_park, @accommodation_id_seoul_1, '온수가 좋아요', 5, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_lee, @accommodation_id_cheongju_1, '전체적으로 디자인이 좋았습니다', 4, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_lee, @accommodation_id_daejeon_1, '이 방 다신 예약 안합니다', 1, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_jeong, @accommodation_id_daejeon_2, '그냥 그랬습니다', 3, 0);

-- 추가 리뷰 추가
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_yu, @accommodation_id_seoul_2, '전망이 너무 좋아요', 5, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_no, @accommodation_id_cheongju_1, '조식이 훌륭합니다', 4, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_park2, @accommodation_id_cheongju_1, '서비스가 최고입니다', 5, 0);
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_jung, @accommodation_id_daejeon_2, '위치가 좋습니다', 4, 0);

INSERT INTO review (member_id, accommodation_id, content, score, is_deleted)
VALUES (@member_id_choi, @accommodation_id_cheongju_2, '대기시간이 길어요', 3, 0);




--review end

--accommodation facility start

INSERT INTO accommodation_facility (name)
VALUES ('주차장'),('편의점'),('레스토랑'),('헬스장'),('수영장'),('와인바'),('바베큐'),('키즈클럽'),('이벤트공간'),('셔틀버스'),('와이파이');

-- --accommodation facility end
--
--accommodation facility mapping start
INSERT INTO accommodation_facility_mapping (accommodation_id,accommodation_facility_id)
VALUES (1,1),(2,1),(2,2);
-- --accommodation facility mapping end

--room facility start

INSERT INTO room_facility (name)
VALUES ('욕조'),('커피포트'),('TV'),('냉장고'),('에어컨'),('조리시설'),('발코니'),('헤어 드라이어'),('욕실용품');

--room facility end
--
--room facility mapping start
INSERT INTO room_facility_mapping (room_id,room_facility_id)
VALUES (1,1),(2,1),(2,2);
--room facility mapping end
--
--accommodationImage start
INSERT INTO accommodation_image (accommodation_id, url)
VALUES
    (1, 'https://i.namu.wiki/i/SJU_Jxqyocs1qbSa9Eh6N7Rlpi1luHi0yLmU19GbcUXa27Q-6snYSoHW0cxteDh-egtRiTkc_SgtJEE_bJcFxA.webp'),
    (2, 'https://dynamic-media-cdn.tripadvisor.com/media/photo-o/26/c4/46/5e/caption.jpg?w=1200&h=-1&s=1'),
    (3, 'https://www.wyndhamhotels.com/content/dam/creative-images/apac/flat/1x1/apac_1x1_57994_gr__kien_giang_province.jpg?downsize=700:*'),
    (4, 'https://www.hotelscombined.co.kr/himg/9c/e3/96/revato-3268-13030741-178971.jpg'),
    (5, 'https://www.p-city.com/mobilePub/static/images/hotelParadise/img_main_visual.jpg'),
    (6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSbz3a8ixymGkEShWLVKAA4QAXXtQ8PznUk9xBeIqGz-A&s');
--accommodationImage end
--
--external summary start
INSERT INTO external_summary (accommodation_id, summary)
VALUES (1,'1번 숙소의 외부 요약'),(2,'2번 숙소의 외부 요약'),(3,'3번 숙소의 외부 요약');
--external summary end

--internal summary start
INSERT INTO internal_summary (accommodation_id, summary)
VALUES (1,'1번 숙소의 내부 요약'),(2,'2번 숙소의 내부 요약'),(3,'3번 숙소의 내부 요약');
-- internal summary end