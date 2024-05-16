package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.AccommodationFacilityMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationFacilityMappingRepository extends JpaRepository<AccommodationFacilityMapping, Long> {
    void deleteAccommodationFacilityMappingByAccommodationId(Long accommodationId);

}
