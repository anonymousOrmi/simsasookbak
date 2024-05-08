package com.simsasookbak.review.service;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.service.MemberService;
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
    private final MemberService memberService;

    public ExternalSummaryResponse save(Long memberId, ExternalSummaryRequest request) {
        Member member = memberService.findById(memberId);
        ExternalSummary externalSummary = request.toEntity(member);

        ExternalSummary savedExternalSummary = externalSummaryRepository.save(externalSummary);

        return new ExternalSummaryResponse(savedExternalSummary);
    }

}
