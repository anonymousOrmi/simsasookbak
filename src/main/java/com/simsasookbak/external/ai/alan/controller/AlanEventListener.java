package com.simsasookbak.external.ai.alan.controller;

import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import com.simsasookbak.external.ai.alan.event.RegistrationEvent;
import com.simsasookbak.external.ai.alan.service.AlanService;
import com.simsasookbak.member.service.MemberService;
import com.simsasookbak.review.dto.ExternalSummaryRequest;
import com.simsasookbak.review.dto.ExternalSummaryResponse;
import com.simsasookbak.review.service.ExternalSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AlanEventListener {

    private final MemberService memberService;
    private final AlanService alanService;
    private final ExternalSummaryService externalSummaryService;

    @EventListener
    public void handleRegistrationEvent(RegistrationEvent event) {
        final ExternalSummaryResponse alanResponse = ExternalSummaryWithAlan(event.getMemberId());
        log.info("AI Comment Response: {}", alanResponse);
    }

    private ExternalSummaryResponse ExternalSummaryWithAlan(@PathVariable Long memberId) {

        final String prompt = memberService.findById(memberId).getName();

        final AlanResponseDto alanResponse = alanService.getAlan(prompt);

        final String summary = alanResponse.getContent();

        ExternalSummaryRequest request = new ExternalSummaryRequest(summary);

        return externalSummaryService.save(memberId, request);
    }


}
