package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.domain.ReviewImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {

    List<ReviewImage> findAllByReview(Review review);
}
