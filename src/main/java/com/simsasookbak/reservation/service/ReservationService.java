package com.simsasookbak.reservation.service;

import static com.simsasookbak.global.exception.ErrorMessage.UNEXPECTED_ROW_COUNT;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.domain.Status;
import com.simsasookbak.reservation.dto.ReservationAddRequestDto;
import com.simsasookbak.reservation.dto.ReservationAddResponseDto;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.dto.request.PopularRegionRequest;
import com.simsasookbak.reservation.dto.response.ReservationResponse;
import com.simsasookbak.reservation.dto.response.ReservationResponseDto;
import com.simsasookbak.reservation.repository.ReservationRepository;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final AccommodationService accommodationService;
    private final RoomService roomService;

    private static LocalDate addDays(LocalDate date, int days) {
        return date.plusDays(days);
    }

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

    public ReservationAddResponseDto save(Member member, Long accommodationId, Long roomId, ReservationAddRequestDto request) {

        AccommodationDto accommodationDto = accommodationService.findAccommodationById(accommodationId);
        Accommodation accommodation = AccommodationDto.toAccommodation(accommodationDto);

        RoomDto roomDto = roomService.findRoomById(roomId);
        Room room = roomDto.toEntity(accommodation);

        Reservation reservation = request.toEntity(member, accommodation, room);
        Reservation savedReservation = reservationRepository.save(reservation);

        return new ReservationAddResponseDto(savedReservation);
    }

    public List<ReservationResponse> findPopularRegionsByDate(PopularRegionRequest request) {
        Pageable pageable = PageRequest.of(0, request.getLimit());
        LocalDateTime diffDatetime = LocalDateTime.now().minusDays(request.getDay());
        return reservationRepository.findPopularRegionsByCreatedAt(diffDatetime, pageable)
                .stream()
                .map(view -> ReservationResponse.builder()
                        .region(view.getRegion())
                        .build()
                )
                .toList();
    }

    public List<ReservationResponse> getExpiredReservations() {
        return reservationRepository.findAllReservationByCurrentDate(Status.EXPIRE.getName())
                .stream()
                .map(ReservationResponse::new)
                .toList();
    }

    @Transactional
    public void updateAllReservation(List<ReservationResponse> expiredReservations, String status) throws SQLException {
        List<Long> idByExpiredReservation = expiredReservations.stream()
                .map(ReservationResponse::getId)
                .toList();
        int rowCount = reservationRepository.updateAllByReservationStatus(idByExpiredReservation, status);

        if (rowCount != expiredReservations.size()) {
            throw new SQLDataException(UNEXPECTED_ROW_COUNT.getMessage());
        }
    }

    public List<String> getReservationRoomName(Long accommodationId, Long reviewWriterMemberId) {
        return reservationRepository.findAllReservationRoomNameById(accommodationId, reviewWriterMemberId);
    }

    public List<ReservationResponseDto> findAllReservationByMemberId(Long id){
        return reservationRepository.findAllReservationByUserId(id).stream().map(ReservationResponseDto::new).collect(
                Collectors.toList());
    }

    public List<ReservationResponseDto> findReservationByAccommodationId(Long id) {
        return reservationRepository.findByAccommodationId(id).stream().map(ReservationResponseDto::new).collect(
                Collectors.toList());
    }
  
    @Transactional
    public void cancelReservation(Long reservationId) {
        reservationRepository.cancelReservationById(reservationId);
    }

    @Transactional
    public void approveReservation(Long reservationId) {
        reservationRepository.approveReservationById(reservationId);
    }
    public void updateReservation(Long reservationId, LocalDate startDate, LocalDate endDate, String requestMessage) {
        reservationRepository.updateReservationById(reservationId, startDate, endDate, requestMessage);
    }

    public ReservationResponseDto findReservationById(Long reservationId){
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto(reservationRepository.findReservationById(reservationId));

        return reservationResponseDto;
    }

}
