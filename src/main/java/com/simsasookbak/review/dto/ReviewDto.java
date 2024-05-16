package com.simsasookbak.review.dto;

import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.domain.ReviewImage;
import java.time.LocalDateTime;
import java.util.List;
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
    private String memberId;
    private Long accommodationId;
    private String content;
    private Integer score;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String formattedCreatedAt;
    private String formattedUpdatedAt;
    private List<String> roomTitle;
    private List<ReviewImage> reviewImages;

    public static ReviewDto toDto(Review review,List<ReviewImage> reviewImages,List<String> roomTitle) {
        return ReviewDto.builder()
                .reviewId(review.getId())
                .accommodationId(review.getAccommodation().getId())
                .memberId(review.getMember().getEmail())
                .memberName(
                        review.getMember().getName())
                .content(review.getContent())
                .score(review.getScore())
                .isDeleted(review.getIsDeleted())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .roomTitle(roomTitle)
                .reviewImages(reviewImages)
                .build();
    }

    public void setFormattedCreatedAt(String formattedCreatedAt) {
        this.formattedCreatedAt = formattedCreatedAt;
    }

    public void setFormattedUpdatedAt(String formattedUpdatedAt) {
        this.formattedUpdatedAt = formattedUpdatedAt;
    }

}
