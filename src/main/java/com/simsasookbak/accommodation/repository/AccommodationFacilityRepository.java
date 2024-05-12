package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.AccommodationFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationFacilityRepository extends JpaRepository<AccommodationFacility, Long> {

    AccommodationFacility findByName(String name);

}
