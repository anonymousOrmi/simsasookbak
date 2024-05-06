package com.simsasookbak.reservation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.dto.ReservationAddRequestDto;
import com.simsasookbak.reservation.dto.ReservationAddResponseDto;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.repository.ReservationRepository;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AccommodationService accommodationService;
    private final RoomService roomService;

    public ReservationUnableDto getReservationUnableDates(Long roomId) {
        List<Reservation> reservations = findAllCompleteStatusRoomByRoomId(roomId);

        ReservationUnableDto reservationUnableDto = new ReservationUnableDto();

        // 각 예약별로 시작일부터 종료일 전날까지의 날짜 => 입실 불가일 리스트
        // 시작일 다음날부터 종료일까지의 날짜 => 퇴실 불가일 리스트
        for (Reservation reservation : reservations) {
            LocalDate startDate = reservation.getStartDate();
            LocalDate endDate = reservation.getEndDate();

            // 입실 불가일 리스트
            LocalDate currentDate = startDate;
            while (currentDate.isBefore(endDate)) {
                reservationUnableDto.addCheckInUnselectableDate(currentDate);
                currentDate = addDays(currentDate, 1);
            }

            // 퇴실 불가일 추가
            currentDate = addDays(startDate, 1); // 시작일 다음날
            while (!currentDate.isAfter(endDate)) {
                reservationUnableDto.addCheckOutUnselectableDate(currentDate);
                currentDate = addDays(currentDate, 1);
            }
        }

        return reservationUnableDto;
    }

    private List<Reservation> findAllCompleteStatusRoomByRoomId(Long roomId) {
        return reservationRepository.findAllCompleteStatusRoomByRoomId(roomId);
    }

    private static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

    public ReservationAddResponseDto save(Long accommodationId, Long roomId, ReservationAddRequestDto request) {

        AccommodationDto accommodationDto = accommodationService.findAccommodationById(accommodationId);
        Accommodation accommodation = AccommodationDto.toAccommodation(accommodationDto);

        RoomDto roomDto = roomService.findRoomById(roomId);
        Room room = roomDto.toEntity(accommodation);

        Reservation reservation = request.toEntity(accommodation, room);
        Reservation savedReservation = reservationRepository.save(reservation);

        return new ReservationAddResponseDto(savedReservation);
    }

}
