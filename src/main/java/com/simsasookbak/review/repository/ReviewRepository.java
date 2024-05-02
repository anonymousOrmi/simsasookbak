package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT a.summary FROM ExternalSummary a WHERE a.accommodation.id = :acom_id")
    String findExSummaryByAcomId(@Param("acom_id") Long acom_id);

    @Query("SELECT a.summary FROM InternalSummary a WHERE a.accommodation.id = :acom_id")
    String findInSummaryByAcomId(@Param("acom_id") Long acom_id);

}
