package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.ExternalSummary;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalSummaryRepository extends JpaRepository<ExternalSummary,Long> {

    @Query("SELECT e FROM ExternalSummary e WHERE e.accommodation.id = :accommodationId")
    Optional<ExternalSummary> findByAccommodationId(@Param("accommodationId")Long accommodationId);

}
