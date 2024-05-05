package com.simsasookbak.reservation.service;

import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.repository.ReservationRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationUnableDto getUnableDays(Long roomId) {
        return null;
    }

    public ReservationUnableDto getReservationUnableDates(Long roomId) {
        List<Reservation> reservations = findAllCompleteStatusRoomByRoomId(roomId);

        ReservationUnableDto reservationUnableDto = new ReservationUnableDto();

        // 각 예약별로 시작일부터 종료일 전날까지의 날짜 => 입실 불가일 리스트
        // 시작일 다음날부터 종료일까지의 날짜 => 퇴실 불가일 리스트
        for (Reservation reservation : reservations) {
            Date startDate = reservation.getStartDate();
            Date endDate = reservation.getEndDate();

            // 입실 불가일 리스트
            Date currentDate = startDate;
            while (currentDate.before(endDate)) {
                reservationUnableDto.addCheckInUnselectableDate(currentDate);
                currentDate = addDays(currentDate, 1);
            }

            // 퇴실 불가일 추가
            currentDate = addDays(startDate, 1); // 시작일 다음날
            while (!currentDate.after(endDate)) {
                reservationUnableDto.addCheckOutUnselectableDate(currentDate);
                currentDate = addDays(currentDate, 1);
            }
        }

        return reservationUnableDto;
    }

    private List<Reservation> findAllCompleteStatusRoomByRoomId(Long roomId) {
        return reservationRepository.findAllCompleteStatusRoomByRoomId(roomId);
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

}
