package com.simsasookbak.review.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.domain.ExternalSummary;
import com.simsasookbak.review.dto.ExternalSummaryRequest;
import com.simsasookbak.review.dto.ExternalSummaryResponse;
import com.simsasookbak.review.repository.ExternalSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ExternalSummaryService {

    private final ExternalSummaryRepository externalSummaryRepository;
    private final AccommodationService accommodationService;

    public ExternalSummaryResponse save(Long accommodationId, ExternalSummaryRequest request) {
        Accommodation accommodation = accommodationService.findById(accommodationId);
        ExternalSummary externalSummary = request.toEntity(accommodation);

        ExternalSummary savedExternalSummary = externalSummaryRepository.save(externalSummary);

        return new ExternalSummaryResponse(savedExternalSummary);
    }

}
