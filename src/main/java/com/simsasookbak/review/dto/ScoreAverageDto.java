package com.simsasookbak.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ScoreAverageDto {

    private Long accommodationId;

    private Double averageScore;

    public ScoreAverageDto( Long accommodationId, Double averageScore) {

        this.accommodationId = accommodationId;
        this.averageScore = averageScore;
    }

}
