package com.simsasookbak.review.repository;

import com.simsasookbak.review.domain.InternalSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InternalSummaryRepository extends JpaRepository<InternalSummary, Long> {
}
