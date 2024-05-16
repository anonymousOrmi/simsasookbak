package com.simsasookbak.review.service;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.domain.InternalSummary;
import com.simsasookbak.review.dto.InternalSummaryRequest;
import com.simsasookbak.review.dto.InternalSummaryResponse;
import com.simsasookbak.review.repository.InternalSummaryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InternalSummaryService {

    private final InternalSummaryRepository internalSummaryRepository;
    private final AccommodationService accommodationService;

    public InternalSummaryResponse save(Long accommodationId, InternalSummaryRequest internalSummaryRequest) {
        Accommodation accommodation = accommodationService.findById(accommodationId);
        InternalSummary internalSummary =  internalSummaryRequest.toEntity(accommodation);

        InternalSummary savedInternalSummary = internalSummaryRepository.save(internalSummary);

        return new InternalSummaryResponse(savedInternalSummary);
    }

    public void resetInternalSummary(Long accommodationId) {
        Optional<InternalSummary> internalSummary = internalSummaryRepository.findByAccommodationId(accommodationId);

        internalSummary.ifPresent(this::delete);
    }

    private void delete(InternalSummary internalSummary) {
        internalSummaryRepository.delete(internalSummary);
    }

}
