package com.simsasookbak.external.ai.alan.event;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import com.simsasookbak.external.ai.alan.service.AlanService;
import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.dto.ExternalSummaryRequest;
import com.simsasookbak.review.dto.ExternalSummaryResponse;
import com.simsasookbak.review.dto.InternalSummaryRequest;
import com.simsasookbak.review.service.ExternalSummaryService;
import com.simsasookbak.review.service.InternalSummaryService;
import com.simsasookbak.review.service.ReviewService;
import java.time.LocalTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlanEventListener {

    private static final String ALAN_EXTERNAL_QUESTION = "이름이 %s인 숙소의 후기를 요약해서 출처를 포함하여 총 150자 내외로 알려줘. 또 별점을 5점 만점으로 해서 매겨줘.";
    private static final String ALAN_INTERNAL_QUESTION = "인 숙소에 대한 리뷰들인데 요약해줘. 숙소 이름, 출처 정보, 별점 정보 필요없으니 알려주지마.";
    private static final String REGEX = "\\[\\(출처(\\d+)\\)\\]|\\((https?://[^\\s]+)\\)";
    private final AccommodationService accommodationService;
    private final AlanService alanService;
    private final ExternalSummaryService externalSummaryService;
    private final ReviewService reviewService;
    private final InternalSummaryService internalSummaryService;

    ///////외부 리뷰 -> 숙소 이벤트
    @EventListener
    @Async
    public void handleAccommodationEvent(RegistrationEvent event) {
        final ExternalSummaryResponse alanResponse = ExternalSummaryWithAlan(event.getAccommodationId());
        log.info("AI Comment Response: {}", alanResponse);
    }

    private ExternalSummaryResponse ExternalSummaryWithAlan(@PathVariable Long accommodationId) {
        checkExternalSummaryExist(accommodationId);

        String accommodationName = accommodationService.findAccommodationById(accommodationId).getName();
        String prompt = String.format(ALAN_EXTERNAL_QUESTION, accommodationName);
        AlanResponseDto alanResponse = alanService.getAlan(prompt);

        String alanAnswer = alanResponse.getContent();
        String regexResult = applyRegexForBackUrl(alanAnswer);

        ExternalSummaryRequest request = new ExternalSummaryRequest(regexResult);

        return externalSummaryService.save(accommodationId, request);
    }

    private void checkExternalSummaryExist(Long accommodationId) {
        externalSummaryService.resetExternalSummary(accommodationId);
    }

    private String applyRegexForBackUrl(String input) {
        StringBuilder sources = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();
            sources.append(match).append(" ");
            input = input.replace(match, "").trim();
        }

        return input.trim() + " " + sources.toString().trim();
    }

    /////내부리뷰 -> 스케쥴링
    //매시 0분 0초에 실행, 테스트시 변경
    @Scheduled(cron = "0 0 * * * *")
    @Async
    public void executeInternalSummaryWithAlan() {
        LocalTime currentTime = LocalTime.now();
        List<Accommodation> accommodations = accommodationService.findAccommodationsByCreatedAtTime(currentTime);

        for (Accommodation accommodation : accommodations) {
            InternalSummaryWithAlan(accommodation);
        }
    }

    private void InternalSummaryWithAlan(Accommodation accommodation) {
        Long accommodationId = accommodation.getId();
        String accommodationName = accommodation.getName();

        checkInternalSummaryExist(accommodationId);

        List<Review> reviews = reviewService.findTop3ReviewsByAccommodationIdAndCreatedAt(accommodationId);
        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append("이름이").append(accommodationName).append(ALAN_INTERNAL_QUESTION);

        for (int i = 0; i < reviews.size(); i++) {
            summaryBuilder.append((i + 1)).append(". ").append(reviews.get(i).getContent());
        }

        final String prompt = summaryBuilder.toString();
        final AlanResponseDto alanResponse = alanService.getAlan(prompt);

        String alanAnswer = alanResponse.getContent();
        final String regexResult = applyRegexForNoUrl(alanAnswer);
        InternalSummaryRequest internalSummaryRequest = new InternalSummaryRequest(regexResult);

        internalSummaryService.save(accommodationId, internalSummaryRequest);
    }

    private void checkInternalSummaryExist(Long accommodationId) {
        internalSummaryService.resetInternalSummary(accommodationId);
    }

    private String applyRegexForNoUrl(String input) {
        StringBuilder removedMatches = new StringBuilder();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String match = matcher.group();
            removedMatches.append(match).append(" ");
            input = input.replace(match, "").trim();
        }

        return input.trim();
    }

}
