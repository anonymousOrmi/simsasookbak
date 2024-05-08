package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.dto.response.AccommodationView;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.review.service.ReviewService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final ReviewService reviewService;

//    @Transactional
//    public List<AccommodationDto> searchAccommodation(String keyword) {
//
//        //공백 처리
//        String processedKeyword = keyword.replaceAll("\\s+", "");
//
//        List<Accommodation> accommodations = accommodationRepository.findKeyword(processedKeyword);
//        List<AccommodationDto> accommodationDtos = new ArrayList<>();
//
//        for (Accommodation accommodation : accommodations) {
//            List<String> facilityList = findAccommodationFacilityById(accommodation.getId());
//            AccommodationDto accommodationDto = AccommodationDto.toAccommodationDto(accommodation, facilityList);
//            accommodationDtos.add(accommodationDto);
//        }
//        return accommodationDtos;
//    }
//
//    @Transactional
//    public List<AccommodationDto> searchDate(Date startDate, Date endDate) {
//        int diff = accommodationRepository.compareStartAndEndDates(startDate, endDate);
//
//        if (diff == 1) {
//            List<Accommodation> availableAccommodations = accommodationRepository.findAvailableAccommodationsByDate(startDate, endDate);
//
//            List<AccommodationDto> accommodationDtos = availableAccommodations.stream()
//                    .map(accommodation -> AccommodationDto.toAccommodationDto(accommodation,findAccommodationFacilityById(accommodation.getId())))
//                    .collect(Collectors.toList());
//
//            Set<Long> notCompletedAccommodationIds = reservationRepository.findNotCompleteStatus(startDate, endDate)
//                    .stream()
//                    .map(reservation -> reservation.getAccommodation().getId())
//                    .collect(Collectors.toSet());
//
//            Set<AccommodationDto> mergedAccommodationDtos = Stream.concat(accommodationDtos.stream(),
//                            notCompletedAccommodationIds.stream()
//                                    .map(accommodationId -> accommodationRepository.findById(accommodationId)
//                                            .orElseThrow(() -> new EntityNotFoundException("Accommodation not found with id: " + accommodationId)))
//                                    .map(accommodation-> AccommodationDto.toAccommodationDto(accommodation,findAccommodationFacilityById(accommodation.getId()))))
//                    .collect(Collectors.toSet());
//
//            return new ArrayList<>(mergedAccommodationDtos);
//        } else {
//            throw new RuntimeException("Date를 명확히 입력");
public Page<AccommodationResponse> searchAccommodations(AccommodationRequest request, int pageNum) {

    Pageable pageable = PageRequest.of(pageNum, 16);
    String keyword = request.getKeyword();
    Page<AccommodationView> page;
    if (request.isEmptyAllDates() && Strings.isNotBlank(keyword)) {
        page = accommodationRepository.findAllByKeyword(keyword.trim(), pageable);
    } else {
        page = accommodationRepository.findAllByStartDateAndEndDate(
                request.getStartDate(),
                request.getEndDate(),
                Strings.isBlank(keyword) ? keyword : keyword.trim(),
                pageable
        );
    }
    return page.map(AccommodationResponse::new);
}

    public List<AccommodationDto> getHighScoreAccommodation() {
        // 상위 6개의 accommodation ID를 가져오기
        List<Long> topSixAccommodationIds = reviewService.findScoreSixAccommodation();

        // 상위 6개의 accommodation ID와 일치하는 accommodation DTO 리스트를 반환합니다.
        List<AccommodationDto> highScoreAccommodations = topSixAccommodationIds.stream()
                .map(accommodationRepository::findAccommodationById)
                .filter(accommodation -> accommodation != null && !accommodation.getIsDeleted()) // 삭제되지 않은 숙소만 필터링
                .map(accommodation -> AccommodationDto.toAccommodationDto(accommodation,findAccommodationFacilityById(accommodation.getId())))
                .collect(Collectors.toList());

        // 리스트에 있는 내용을 for문을 사용하여 확인합니다.
        for (AccommodationDto accommodationDto : highScoreAccommodations) {
            System.out.println("Accommodation ID: " + accommodationDto.getId());
        }

        return highScoreAccommodations;
    }

    public AccommodationDto findAccommodationById(Long id) {
//<<<<<<< HEAD
        Accommodation accommodation = accommodationRepository.findAccommodationById(id);
        List<String> facilityList = findAccommodationFacilityById(id);

        return AccommodationDto.toAccommodationDto(accommodation,facilityList);
    }

    public List<String> findAccommodationFacilityById(Long id) {
        return accommodationRepository.findAccommodationFacilityById(id);
    }


    public List<String> findImgByAcomId(Long id) {
        return accommodationRepository.findImgByAcomId(id);
    }

//=======
//        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow();
//        return AccommodationDto.toAccommodationDto(accommodation);
//    }
//
//    /*public Accommodation findAccommodationById(Long id) {
//        return accommodationRepository.findAccommodationById(id);
//    }*/
//>>>>>>> develop
}



