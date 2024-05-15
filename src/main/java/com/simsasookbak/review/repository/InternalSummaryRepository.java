package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.InternalSummary;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InternalSummaryRepository extends JpaRepository<InternalSummary, Long> {

    @Query("SELECT i FROM InternalSummary i WHERE i.accommodation.id = :accommodationId")
    Optional<InternalSummary> findByAccommodationId(@Param("accommodationId")Long accommodationId);

}
