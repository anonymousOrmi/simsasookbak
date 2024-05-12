package com.simsasookbak.external.ai.alan.controller;

import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import com.simsasookbak.external.ai.alan.event.RegistrationEvent;
import com.simsasookbak.external.ai.alan.service.AlanService;
import com.simsasookbak.review.dto.ExternalSummaryRequest;
import com.simsasookbak.review.dto.ExternalSummaryResponse;
import com.simsasookbak.review.service.ExternalSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlanEventListener {

    private static final String ALAN_QUESTION = "숙소의 후기를 요약해서 100자 내외로 알려주는데 출처는 붙이지마. 또 별점을 5점 만점으로 해서 매겨줘.";
    private final AccommodationService accommodationService;
    private final AlanService alanService;
    private final ExternalSummaryService externalSummaryService;

    @EventListener
    @Async
    public void handleRegistrationEvent(RegistrationEvent event) {
        final ExternalSummaryResponse alanResponse = ExternalSummaryWithAlan(event.getAccommodationId());
        log.info("AI Comment Response: {}", alanResponse);
    }

    private ExternalSummaryResponse ExternalSummaryWithAlan(@PathVariable Long accommodationID) {

        final String accommodationName =accommodationService.findAccommodationById(accommodationID).getName();

        final String prompt = accommodationName + ALAN_QUESTION;

        final AlanResponseDto alanResponse = alanService.getAlan(prompt);

        final String summary = alanResponse.getContent();

        ExternalSummaryRequest request = new ExternalSummaryRequest(summary);

        return externalSummaryService.save(accommodationID, request);
    }


}
