package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.AccommodationImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationImageRepository extends JpaRepository<AccommodationImage,Long> {

}
