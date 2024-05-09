package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import com.simsasookbak.review.dto.ScoreAverageDto;
import com.simsasookbak.review.service.ReviewService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final ReviewService reviewService;

    public List<AccommodationResponse> searchAccommodations(AccommodationRequest request) {
        String keyword = request.getKeyword();
        if (request.isEmptyAllDates() && Strings.isNotBlank(request.getKeyword())) {
            return accommodationRepository.findAllByKeyword(keyword.trim())
                    .stream()
                    .map(AccommodationResponse::new)
                    .toList();
        }
        return accommodationRepository.findAllByStartDateAndEndDate(
                        request.getStartDate(),
                        request.getEndDate(),
                        Strings.isBlank(keyword) ? keyword : keyword.trim()
                )
                .stream()
                .map(AccommodationResponse::new)
                .toList();
    }

    public List<AccommodationDto> getHighScoreAccommodation() {
        // 상위 6개의 accommodation ID를 가져오기
        List<ScoreAverageDto> topSixAccommodations = reviewService.findScoreSixAccommodation();

        List<Double> avgScoreList = topSixAccommodations.stream().map(ScoreAverageDto::getAverageScore).collect(
                Collectors.toList());

        // 상위 6개의 accommodation ID와 일치하는 accommodation DTO 리스트를 반환합니다.
        List<AccommodationDto> highScoreAccommodations = topSixAccommodations.stream()
                .map(accommodation -> accommodationRepository.findAccommodationById(accommodation.getAccommodationId()))
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
}



