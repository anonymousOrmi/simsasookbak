package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {


    @Query("SELECT a FROM Accommodation a WHERE a.region LIKE %:keyword% OR a.name LIKE %:keyword%")
    List<Accommodation> findKeyword(String keyword);
}
