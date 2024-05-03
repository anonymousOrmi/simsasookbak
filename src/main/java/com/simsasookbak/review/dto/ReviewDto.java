package com.simsasookbak.review.dto;

import com.simsasookbak.review.domain.Review;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    private Long reviewId;
    private String memberName;
    private Long accommodationId;
    private String content;
    private Integer score;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .accommodationId(review.getAccommodation().getId())
                .memberName(
                        review.getMember().getName())
                .content(review.getContent())
                .score(review.getScore())
                .isDeleted(review.getIsDeleted())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

}
