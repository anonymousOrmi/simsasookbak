package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.domain.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewImageRepository extends JpaRepository<ReviewImage,Long> {

    List<ReviewImage> findAllByReview(Review review);


//    @Query(value = "INSERT INTO review_image(review_id,url) VALUES (:reviewId , :url)")
//    void saveToDb(Long reviewId,String url);
}
