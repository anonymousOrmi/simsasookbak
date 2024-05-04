package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.repository.ReservationRepository;
import com.simsasookbak.review.service.ReviewService;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import com.simsasookbak.review.domain.ExternalSummary;
import com.simsasookbak.room.domain.Room;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final ReviewService reviewService;

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
    public List<AccommodationDto> searchDate(Date startDate, Date endDate) {
        int diff = accommodationRepository.compareStartAndEndDates(startDate, endDate);

        if (diff == 1) {
            List<Accommodation> availableAccommodations = accommodationRepository.findAvailableAccommodationsByDate(startDate, endDate);

            List<AccommodationDto> accommodationDtos = availableAccommodations.stream()
                    .map(AccommodationDto::toAccommodationDto)
                    .collect(Collectors.toList());

            Set<Long> notCompletedAccommodationIds = reservationRepository.findNotCompleteStatus(startDate, endDate)
                    .stream()
                    .map(reservation -> reservation.getAccommodation().getId())
                    .collect(Collectors.toSet());

            Set<AccommodationDto> mergedAccommodationDtos = Stream.concat(accommodationDtos.stream(),
                            notCompletedAccommodationIds.stream()
                                    .map(accommodationId -> accommodationRepository.findById(accommodationId)
                                            .orElseThrow(() -> new EntityNotFoundException("Accommodation not found with id: " + accommodationId)))
                                    .map(AccommodationDto::toAccommodationDto))
                    .collect(Collectors.toSet());

            return new ArrayList<>(mergedAccommodationDtos);
        } else {
            throw new RuntimeException("Date를 명확히 입력");
        }
    }

    @Transactional
    public List<AccommodationDto> getHighScoreAccommodation() {
        // 상위 6개의 accommodation ID를 가져오기
        List<Long> topSixAccommodationIds = reviewService.findScoreSixAccommodation();


        // 상위 6개의 accommodation ID와 일치하는 accommodation DTO 리스트를 반환합니다.
        List<AccommodationDto> highScoreAccommodations = topSixAccommodationIds.stream()
                .map(accommodationRepository::findAccommodationById)
                .filter(accommodation -> accommodation != null && !accommodation.getIsDeleted()) // 삭제되지 않은 숙소만 필터링
                .map(AccommodationDto::toAccommodationDto)
                .collect(Collectors.toList());


        // 리스트에 있는 내용을 for문을 사용하여 확인합니다.
        for (AccommodationDto accommodationDto : highScoreAccommodations) {
            System.out.println("Accommodation ID: " + accommodationDto.getId());
        }

        return highScoreAccommodations;
    }


//스트림 안 쓴 searh문 삭제 하지 말아주세요 !
//    @Transactional
//    public List<AccommodationDto> searchDate(Date startDate, Date endDate) {
//
//        // startDate, endDate 명확히 받았는지 확인
//        int diff = accommodationRepository.compareStartAndEndDates(startDate, endDate);
//
//        if (diff == 1) {
//
//            // 예약 없고, startDate, endDate 일치
//            List<Accommodation> availableAccommodations = accommodationRepository.findAvailableAccommodationsByDate(
//                    startDate, endDate);
//
//            // Convert to DTOs
//            List<AccommodationDto> accommodationDtos = availableAccommodations.stream()
//                    .map(AccommodationDto::toAccommodationDto)
//                    .collect(Collectors.toList());
//
//            // 완료 상태가 아닌 숙박 시설의 ID 가져오기
//            List<Long> notCompletedAccommodationIds = reservationRepository.findNotCompleteStatus(startDate, endDate)
//                    .stream()
//                    .map(reservation -> reservation.getAccommodation().getId())
//                    .distinct()
//                    .collect(Collectors.toList());
//
//            // AccommodationDto 리스트 생성
//            List<AccommodationDto> notCompletedAccommodationDtos = new ArrayList<>();
//
//            // AccommodationId를 이용하여 AccommodationDto 생성 및 리스트에 추가
//            for (Long accommodationId : notCompletedAccommodationIds) {
//                Accommodation accommodation = accommodationRepository.findById(accommodationId)
//                        .orElseThrow(() -> new EntityNotFoundException("Accommodation not found with id: " + accommodationId));
//                AccommodationDto accommodationDto = AccommodationDto.toAccommodationDto(accommodation);
//                notCompletedAccommodationDtos.add(accommodationDto);
//            }
//
//            Set<AccommodationDto> accommodationDtoSet = new HashSet<>(accommodationDtos);
//            Set<AccommodationDto> notCompletedAccommodationDtoSet = new HashSet<>(notCompletedAccommodationDtos);
//
//            // 중복된 ID가 없는 Set으로 병합
//            Set<AccommodationDto> mergedAccommodationDtos = new HashSet<>();
//            for (AccommodationDto accommodationDto : accommodationDtoSet) {
//                if (!containsId(mergedAccommodationDtos, accommodationDto.getId())) {
//                    mergedAccommodationDtos.add(accommodationDto);
//                }
//            }
//
//            for (AccommodationDto accommodationDto : notCompletedAccommodationDtoSet) {
//                if (!containsId(mergedAccommodationDtos, accommodationDto.getId())) {
//                    mergedAccommodationDtos.add(accommodationDto);
//                }
//            }
//
//            // List로 변환
//            List<AccommodationDto> mergedAccommodationDtoList = new ArrayList<>(mergedAccommodationDtos);
//
//            // 출력
//            for (AccommodationDto accommodationDto : mergedAccommodationDtoList) {
//                System.out.println(accommodationDto);
//            }
//
//            // Return the merged list
//            return mergedAccommodationDtoList;
//
//        } else {
//            throw new RuntimeException("Date를 명확히 입력");
//        }
//    }
//
//    private boolean containsId(Set<AccommodationDto> set, Long id) {
//        for (AccommodationDto dto : set) {
//            if (dto.getId().equals(id)) {
//                return true;
//            }
//        }
//        return false;
//    }



    public Accommodation findAccommodationById(Long id) {
        return accommodationRepository.findAccommodationById(id);
    }


//    public List<String> findImgByAcomId(Long id) {
//        return accommodationRepository.findImgByAcomId(id);
//    }

}




