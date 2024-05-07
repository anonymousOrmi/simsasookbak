package com.simsasookbak.accommodation.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.repository.AccommodationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {
    private final AccommodationRepository accommodationRepository;

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
  
    public AccommodationDto findAccommodationById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow();
        return AccommodationDto.toAccommodationDto(accommodation);

    public Accommodation findAccommodationById(Long id) {
        return accommodationRepository.findAccommodationById(id);
    }
}




