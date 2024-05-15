package com.simsasookbak.review.dto;

import com.simsasookbak.review.domain.InternalSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InternalSummaryResponse {

    private Long id;
    private String summary;
    private Long accommodationId;

    public InternalSummaryResponse(InternalSummary internalSummary) {
        this.id = internalSummary.getId();
        this.summary = internalSummary.getSummary();
        this.accommodationId = internalSummary.getAccommodation().getId();
    }

}
