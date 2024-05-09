package com.simsasookbak.review.dto;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.review.domain.ExternalSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExternalSummaryRequest {

    private String summary;

    public ExternalSummary toEntity(Member member) {
        return ExternalSummary.builder().member(member).summary(summary).build();
    }
}
