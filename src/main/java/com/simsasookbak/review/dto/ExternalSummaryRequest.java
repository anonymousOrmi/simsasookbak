package com.simsasookbak.review.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.review.domain.ExternalSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExternalSummaryRequest {

    private String summary;

    public ExternalSummary toEntity(Accommodation accommodation) {
        return ExternalSummary.builder().accommodation(accommodation).summary(summary).build();
    }
}
