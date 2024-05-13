-- Insert dummy data for Member
-- 1234
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('test1@gmail.com', '김상형', 'USER', '1900-05-01', '$2a$12$GiLgp9UrAqlaVIxfbHq4peMVsmMcJOT78jmhee2MRH7S3w1p4lhfi', '일반', '010-1234-1234');
SET @member_id_kim = LAST_INSERT_ID();
-- 12345
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('test2@gmail.com', '박지은', 'BUSINESS', '1920-06-02', '$2a$12$ButAQozslP.KtYJWCnilWezIyN5rodbNrvQvrjEHk0IZ86E35JHge', '일반', '010-2345-1735');
SET @member_id_park = LAST_INSERT_ID();
-- 123456
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('test3@gmail.com', '송찬혁', 'ADMIN', '1950-02-03', '$2a$12$MeL9ZRf0LkL2buqGxhGoVenE6gwMgy1Aj3AciuKkvZs3BvTIvtD6K', '일반', '010-3716-5576');
SET @member_id_song = LAST_INSERT_ID();
-- 1234567
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('test4@gmail.com', '이영석', 'USER', '1970-12-21', '$2a$12$Cl.T37Nntk8Q9Uzdgj2NRu/o930DKBshwIOTNBkyZQrhx4.bGuGK.', '일반', '010-3742-9985');
SET @member_id_lee = LAST_INSERT_ID();
-- 12345678
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('test5@gmail.com', '정민석', 'USER', '1985-12-21', '$2a$12$y7h/Jux3BV6mXrhmzRf33OxPm709Y/Fw62B0ZG/dyChpoVYHu5piS', '일반', '010-7324-5421');
SET @member_id_jeong = LAST_INSERT_ID();
-- 123456789
INSERT INTO member (email, name, role, birth_date, password, status, phone)
VALUES ('test6@gmail.com', '최보현', 'BUSINESS', '2000-08-01', '$2a$12$qU9kKWpGjrbmgavQU4/VOeU3CHBIqgpEuDHo4JvvENKX5ZfhhPUWe', '일반', '010-6785-3452');
SET @member_id_choi = LAST_INSERT_ID();
--member end

--accommodation start

INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_park, '숙소1', '숙소 설명 1', '서울', '00구 ~','08:00:00', '12:00:00', false);
SET @accommodation_id_seoul = LAST_INSERT_ID();

INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_park, '숙소2', '숙소 설명 2', '청주','00구 ~', '10:00:00', '14:00:00', false);
SET @accommodation_id_cheongju = LAST_INSERT_ID();

INSERT INTO accommodation (member_id, name, content, region, address, check_in, check_out, is_deleted)
VALUES (@member_id_choi, '숙소3', '숙소 설명 3', '대전','00구 ~', '14:00:00', '18:00:00', false);
SET @accommodation_id_daejeon = LAST_INSERT_ID();

--accommodation end

--room start

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_seoul, '일방', 10000, '일방', '창문을 닫아주세요');
SET @room_id_one = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_seoul, '일-2방', 10000, '일-2방', '창문을 닫아주세요');
SET @room_id_one_2 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_cheongju, '이방', 20000, '이방이방', '창문을 닫아주세요');
SET @room_id_two = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_cheongju, '이-2방', 20000, '이방2이방', '창문을 닫아주세요');
SET @room_id_two_2 = LAST_INSERT_ID();

INSERT INTO room (accommodation_id, name, cost, content, use_guide)
VALUES (@accommodation_id_daejeon, '삼방', 30000, '삼방삼방삼방', '창문을 닫아주세요');
SET @room_id_three = LAST_INSERT_ID();

--room end

--reservation start

INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_park, @accommodation_id_seoul, @room_id_one, '대기', '2024-05-10', '2024-05-12', '공짜로 해주세요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_park, @accommodation_id_seoul, @room_id_one, '만료', '2024-04-15', '2024-04-20', '공짜로 해주세요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_park, @accommodation_id_seoul, @room_id_one, '완료', '2024-05-09', '2024-05-12', '공짜로 해주세요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_park, @accommodation_id_seoul, @room_id_one, '완료', '2024-05-14', '2024-05-15', '공짜로 해주세요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_park, @accommodation_id_seoul, @room_id_one, '완료', '2024-05-20', '2024-05-25', '공짜로 해주세요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_lee, @accommodation_id_cheongju, @room_id_two, '완료', '2024-05-20', '2024-05-30', '이 방 제가 살게요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_lee, @accommodation_id_cheongju, @room_id_two_2, '완료', '2024-05-20', '2024-05-30', '이 방 제가 살게요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_lee, @accommodation_id_daejeon, @room_id_three, '대기', '2024-05-04', '2024-05-05', '잠깐 다녀갈게요');
INSERT INTO reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request)
VALUES (@member_id_jeong, @accommodation_id_daejeon, @room_id_three, '대기', '2024-05-05', '2024-05-06', '깨끗하게 해주세요');

--reservation end

--review start

INSERT INTO review (member_id, accommodation_id, content, score, is_deleted, room_title)
VALUES (@member_id_park, @accommodation_id_seoul, '침대가 좋아요',5,0,'일반');
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted, room_title)
VALUES (@member_id_park, @accommodation_id_seoul, '배게가 좋아요',5,0,'일반');
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted, room_title)
VALUES (@member_id_park, @accommodation_id_seoul, '온수가 좋아요',5,0,'일반');
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted, room_title)
VALUES (@member_id_lee, @accommodation_id_cheongju, '전체적으로 디자인이 좋았습니다',4,0,'일반');
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted, room_title)
VALUES (@member_id_lee, @accommodation_id_daejeon, '이 방 다신 예약 안합니다',1,0,'일반');
INSERT INTO review (member_id, accommodation_id, content, score, is_deleted, room_title)
VALUES (@member_id_jeong, @accommodation_id_daejeon, '그냥 그랬습니다',3,0,'일반');

--review end

--accommodation facility start

INSERT INTO accommodation_facility (name)
VALUES ('주차장'),('편의점'),('레스토랑'),('헬스장'),('수영장'),('와인바'),('바베큐'),('키즈클럽'),('이벤트공간'),('셔틀버스'),('와이파이');

--accommodation facility end

--accommodation facility mapping start
INSERT INTO accommodation_facility_mapping (accommodation_id,accommodation_facility_id)
VALUES (1,1),(2,1),(2,2);
--accommodation facility mapping end

--room facility start

INSERT INTO room_facility (name)
VALUES ('욕조'),('커피포트'),('TV'),('냉장고'),('에어컨'),('조리시설'),('발코니'),('헤어 드라이어'),('욕실용품');

--room facility end

--room facility mapping start
INSERT INTO room_facility_mapping (room_id,room_facility_id)
VALUES (1,1),(2,1),(2,2);
--room facility mapping end

--accommodationImage start
INSERT INTO accommodation_image (accommodation_id,url)
VALUES (1,'https://i.namu.wiki/i/_VdL80a6q8YfJ3ob0cH0g6M4C4u3eafyHQV8oHFnZetT7yEjHPC8hybEh7-Xwfz6H6S4EkwBn6mkLvhb7rGscQ.webp'),(2,'https://youonejae.com/kor/accommodation/images/accommodation01.png'),(3,'https://q-xx.bstatic.com/xdata/images/hotel/max500/311482139.jpg?k=569f279a3105dfafe82cf60a10f722f4ef24fbfff07cf0954ef484385cbabb87&o=');
--accommodationImage end

--external summary start
INSERT INTO external_summary (accommodation_id, summary)
VALUES (1,'1번 숙소의 외부 요약'),(2,'2번 숙소의 외부 요약'),(3,'3번 숙소의 외부 요약');
--external summary end

--internal summary start
INSERT INTO internal_summary (accommodation_id, summary)
VALUES (1,'1번 숙소의 내부 요약'),(2,'2번 숙소의 내부 요약'),(3,'3번 숙소의 내부 요약');
--internal summary end

-- -- INSERT INTO member (email, name, password, role, birth_date, status, phone) VALUES
-- --                                                                                 ('user1@example.com', 'User 1', 'password1', '이용자', '1990-01-01', '일반', '010-1234-5678'),
-- --                                                                                 ('user2@example.com', 'User 2', 'password2', '이용자', '1995-05-15', '일반', '010-2345-6789');
-- -- --member start
--
-- -- -- Insert dummy data for accommodation
-- -- INSERT INTO accommodation (name, content, region, check_in, check_out, is_deleted)
-- -- VALUES ('숙소1', '숙소 설명 1', '서울', '08:00:00', '12:00:00', false);
-- --
-- -- INSERT INTO accommodation (name, content, region, check_in, check_out, is_deleted)
-- -- VALUES ('숙소3', '숙소 설명 3', '대전', '14:00:00', '18:00:00', false);
--
--
--
--
--
--
-- -- INSERT INTO room (name, cost, content, use_guide, accommodation_id) VALUES
-- --                                                                         ('Room 1', 10, 'room 1', 'Usage guide for room 1', 1),
-- --                                                                         ('Room 2', 15, 'room 2', 'Usage guide for room 2', 1),
-- --                                                                         ('Room 3', 20, 'room 3', 'Usage guide for room 3', 2);
-- --
-- --






-- -- Insert dummy data for Reservation
-- INSERT INTO Reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request) VALUES
--                                                                                                           (1, 1, 1, '대기', '2024-05-10', '2024-05-15', '특이사항 없음'),
--                                                                                                           (2, 1, 2, '완료', '2024-06-01', '2024-06-05', '조용한 방을 원합니다'),
--                                                                                                           (3, 2, 3, '대기', '2024-07-10', '2024-07-15', '친구들과 함께 예약했습니다'),
--                                                                                                           (1, 2, 1, '만료', '2024-08-20', '2024-08-25', '휴가 기간에 이용할 예정입니다'),
