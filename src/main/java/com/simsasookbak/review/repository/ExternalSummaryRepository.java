package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.ExternalSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalSummaryRepository extends JpaRepository<ExternalSummary,Long> {
}
