-- Insert dummy data for Member
INSERT INTO Member (email, name, password, role, birth_date, status, phone) VALUES
                                                                                ('user1@example.com', 'User 1', 'password1', '이용자', '1990-01-01', '일반', '010-1234-5678'),
                                                                                ('user2@example.com', 'User 2', 'password2', '이용자', '1995-05-15', '일반', '010-2345-6789');
-- Insert dummy data for accommodation
INSERT INTO accommodation (name, content, region, check_in, check_out, is_deleted)
VALUES ('숙소1', '숙소 설명 1', '서울', '08:00:00', '12:00:00', false);

INSERT INTO accommodation (name, content, region, check_in, check_out, is_deleted)
VALUES ('숙소2', '숙소 설명 2', '청주', '10:00:00', '14:00:00', false);

INSERT INTO accommodation (name, content, region, check_in, check_out, is_deleted)
VALUES ('숙소3', '숙소 설명 3', '대전', '14:00:00', '18:00:00', false);


INSERT INTO Room (name, cost, content, use_guide, accommodation_id) VALUES
                                                                        ('Room 1', 10, 'room 1', 'Usage guide for room 1', 1),
                                                                        ('Room 2', 15, 'room 2', 'Usage guide for room 2', 1),
                                                                        ('Room 3', 20, 'room 3', 'Usage guide for room 3', 2);

-- -- Insert dummy data for Reservation
-- INSERT INTO Reservation (member_id, accommodation_id, room_id, status, start_date, end_date, request) VALUES
--                                                                                                           (1, 1, 1, '대기', '2024-05-10', '2024-05-15', '특이사항 없음'),
--                                                                                                           (2, 1, 2, '완료', '2024-06-01', '2024-06-05', '조용한 방을 원합니다'),
--                                                                                                           (3, 2, 3, '대기', '2024-07-10', '2024-07-15', '친구들과 함께 예약했습니다'),
--                                                                                                           (1, 2, 1, '만료', '2024-08-20', '2024-08-25', '휴가 기간에 이용할 예정입니다'),
--                                                                                                           (4, 3, 2, '대기', '2024-09-05', '2024-09-10', '조식 포함 여부를 확인해주세요');
