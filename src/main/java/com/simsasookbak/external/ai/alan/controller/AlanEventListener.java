package com.simsasookbak.external.ai.alan.controller;

import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import com.simsasookbak.external.ai.alan.event.RegistrationEvent;
import com.simsasookbak.external.ai.alan.service.AlanService;
import com.simsasookbak.review.dto.ExternalSummaryRequest;
import com.simsasookbak.review.dto.ExternalSummaryResponse;
import com.simsasookbak.review.service.ExternalSummaryService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    private static final String ALAN_QUESTION = "숙소의 후기를 요약해서 100자 내외로 알려줘. 또 별점을 5점 만점으로 해서 매겨줘.";
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

        String alanAnswer = alanResponse.getContent();

        final String regexResult = applyRegex(alanAnswer);

        ExternalSummaryRequest request = new ExternalSummaryRequest(regexResult);

        return externalSummaryService.save(accommodationID, request);
    }

    public String applyRegex(String input) {
        StringBuilder sources = new StringBuilder();
        Pattern pattern = Pattern.compile("\\[\\(출처(\\d+)\\)\\]|\\((https?://[^\\s]+)\\)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();
            sources.append(match).append(" ");
            input = input.replace(match, "").trim();
        }

        return input.trim() + " " + sources.toString().trim();
    }
}
