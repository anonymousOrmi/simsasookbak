package com.simsasookbak.review.dto;

import com.simsasookbak.review.domain.ExternalSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExternalSummaryResponse {

    private Long id;
    private String summary;
    private Long memberId;

    public ExternalSummaryResponse(ExternalSummary externalSummary) {
        this.id = externalSummary.getId();
        this.summary = externalSummary.getSummary();
        this.memberId = externalSummary.getMember().getId();
    }
}
