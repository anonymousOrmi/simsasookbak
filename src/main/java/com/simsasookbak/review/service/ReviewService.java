package com.simsasookbak.review.service;

import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.dto.ReviewDto;
import com.simsasookbak.review.repository.ReviewRepository;
import java.util.List;
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

    public String findExSummaryByAcomId(Long id) {
        return reviewRepository.findExSummaryByAcomId(id);
    }

    public String findInSummaryByAcomId(Long id) {
        return reviewRepository.findInSummaryByAcomId(id);
    }

    public List<ReviewDto> findAllReviewByAcomId(Long id) {
        return reviewRepository.findAllReviewByAcomId(id).stream().map(ReviewDto::toDto).collect(Collectors.toList());
    }
}
