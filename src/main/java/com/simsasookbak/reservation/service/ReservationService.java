package com.simsasookbak.reservation.service;

import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.repository.ReservationRepository;
import java.util.ArrayList;
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

    public List<Date> getCheckInUnselectableDateList(Long roomId) {
        List<Reservation> reservations = findAllByRoomId(roomId);

        List<Date> checkInUnselectableDateList = new ArrayList<>();

        // 각 예약별로 시작일부터 종료일 전날까지의 날짜 => 입실 불가일 리스트
        for (Reservation reservation : reservations) {
            Date startDate = reservation.getStartDate();
            Date endDate = reservation.getEndDate();

            Date currentDate = startDate;
            while (currentDate.before(endDate)) {
                checkInUnselectableDateList.add(currentDate);
                currentDate = addDays(currentDate, 1);
            }
        }

        return checkInUnselectableDateList;
    }

    private List<Reservation> findAllByRoomId(Long roomId) {
        return reservationRepository.findAllByRoomId(roomId);
    }

    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

}
