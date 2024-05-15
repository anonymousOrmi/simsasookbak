package com.simsasookbak.review.service;

import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.domain.ReviewImage;
import com.simsasookbak.review.dto.ReviewDto;
import com.simsasookbak.review.dto.ScoreAverageDto;
import com.simsasookbak.review.repository.ReviewImageRepository;
import com.simsasookbak.review.repository.ReviewRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;

    public ReviewDto findReviewById(Long reviewId, Long memberId) {
        Review review = reviewRepository.findByIdAndMemberIdAndIsDeletedFalse(reviewId, memberId);
        List<ReviewImage> reviewImages = reviewImageRepository.findAllByReview(review);
        return ReviewDto.toDto(review, reviewImages);
    }

    public String findExSummaryByAcomId(Long id) {
        return reviewRepository.findExSummaryByAcomId(id);
    }

    public String findInSummaryByAcomId(Long id) {
        return reviewRepository.findInSummaryByAcomId(id);
    }

    public List<ReviewDto> findAllReviewByAcomId(Long id) {
        List<ReviewDto> reviewList = reviewRepository.findAllReviewByAcomId(id).stream()
                .map(review -> ReviewDto.toDto(review, reviewImageRepository.findAllByReview(review)))
                .collect(Collectors.toList());
        for (ReviewDto reviewDto : reviewList) {
            reviewDto.setFormattedCreatedAt(reviewDto.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            reviewDto.setFormattedUpdatedAt(reviewDto.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        return reviewList;
    }

    public List<ScoreAverageDto> findScoreSixAccommodation() {
//         accommodation ID별 평균 점수를 조회합니다.
        List<ScoreAverageDto> averageScoresByAccommodationId = reviewRepository.findAverageScoreByAccommodationId();

        for (ScoreAverageDto scoreAverageDto : averageScoresByAccommodationId) {
            Long accommodationId = scoreAverageDto.getAccommodationId();
            Double averageScore = scoreAverageDto.getAverageScore();
            System.out.println("Accommodation ID: " + accommodationId + ", Average Score: " + averageScore);
        }
        return averageScoresByAccommodationId;
    }

    @Transactional
    public Review registComment(Review review) {
        return reviewRepository.save(review);
    }


    @Transactional
    public void registReviewImage(String url, Long reviewId) {
        ReviewImage reviewImage = new ReviewImage(reviewRepository.findById(reviewId).orElseThrow(), url);
        reviewImageRepository.save(reviewImage);
    }

    public List<ReviewImage> findAllImageByReviewId(Review reviewId) {
        return reviewImageRepository.findAllByReview(reviewId);
    }

    public List<Review> findTop3ReviewsByAccommodationIdAndCreatedAt(Long accommodationId) {
        LocalDateTime lastSevenDays = LocalDateTime.now().minusDays(7);
        List<Review> reviews = reviewRepository.findReviewsByAccommodationIdAndCreatedAt(accommodationId,
                lastSevenDays);

        if (reviews.size() > 3) {
            return reviews.subList(0, 3);
        } else {
            return reviews;
        }
    }

    @Transactional
    public Review modify(Long id, String content, Integer score) {
        Review review = reviewRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        review.modify(content, score);
        return review;
    }


    public Double getAccommodationScore(Long accommodationId) {
        return reviewRepository.findAverageScoreByAccommodationId(accommodationId).orElse(0.0);
    }

    @Transactional
    public void deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(IllegalArgumentException::new);
        review.changeToDelete();

    }
}
