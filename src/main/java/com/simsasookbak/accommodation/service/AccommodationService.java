package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.repository.ReservationRepository;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import com.simsasookbak.review.domain.ExternalSummary;
import com.simsasookbak.room.domain.Room;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public List<AccommodationDto> searchAccommodation(String keyword) {

        //공백 처리
        String processedKeyword = keyword.replaceAll("\\s+", "");

        List<Accommodation> accommodations = accommodationRepository.findKeyword(processedKeyword);
        List<AccommodationDto> accommodationDtos = new ArrayList<>();

        for (Accommodation accommodation : accommodations) {
            AccommodationDto accommodationDto = AccommodationDto.toAccommodationDto(accommodation);
            accommodationDtos.add(accommodationDto);
        }
        return accommodationDtos;
    }

    @Transactional
    public List<AccommodationDto> searchMain(String keyword, Date startDate, Date endDate) {
        // 날짜 유효성 검사
        int diff = accommodationRepository.compareStartAndEndDates(startDate, endDate);
        if (diff != 1) {
            throw new IllegalArgumentException("유효하지 않은 날짜 범위입니다.");
        }

        // 키워드 공백 제거
        String processedKeyword = keyword.replaceAll("\\s+", "");

        // 사용 가능한 숙박 시설 검색
        List<Accommodation> availableAccommodations = accommodationRepository.findAvailableAccommodationsByDate(processedKeyword, startDate, endDate);

        // 예약 상태가 완료가 아닌 숙박 시설 ID 검색
        Set<Long> notCompletedAccommodationIds = reservationRepository.findNotCompleteStatus(processedKeyword, startDate, endDate)
                .stream()
                .map(reservation -> reservation.getAccommodation().getId())
                .collect(Collectors.toSet());

        // 중복 제거 및 유니크한 숙박 시설 DTO 리스트 생성
        List<AccommodationDto> uniqueAccommodationsList = availableAccommodations.stream()
                .map(AccommodationDto::toAccommodationDto)
                .collect(Collectors.toList());

        // 중복되지 않은 예약되지 않은 숙박 시설 추가
        for (Long accommodationId : notCompletedAccommodationIds) {
            AccommodationDto dto = accommodationRepository.findById(accommodationId)
                    .map(AccommodationDto::toAccommodationDto)
                    .orElseThrow(() -> new EntityNotFoundException("ID에 해당하는 숙박 시설을 찾을 수 없습니다: " + accommodationId));

            // 중복된 ID가 없으면 리스트에 추가
            if (!uniqueAccommodationsList.stream().anyMatch(accommodationDto -> accommodationDto.getId().equals(dto.getId()))) {
                uniqueAccommodationsList.add(dto);
            }
        }

        // 데이터 출력 (디버깅 용도)
        System.out.println("Unique Accommodation Dtos:");
        uniqueAccommodationsList.forEach(dto -> System.out.println("Accommodation ID: " + dto.getId()));

        return uniqueAccommodationsList;
    }


    public Accommodation findAccommodationById(Long id) {
        return accommodationRepository.findAccommodationById(id);
    }


//    public List<String> findImgByAcomId(Long id) {
//        return accommodationRepository.findImgByAcomId(id);
//    }

}




