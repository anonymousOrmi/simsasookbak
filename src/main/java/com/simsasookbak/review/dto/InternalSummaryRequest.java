package com.simsasookbak.review.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.review.domain.InternalSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InternalSummaryRequest {

    private String summary;

    public InternalSummary toEntity(Accommodation accommodation) {
        return InternalSummary.builder().accommodation(accommodation).summary(summary).build();
    }

}
