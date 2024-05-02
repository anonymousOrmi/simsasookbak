package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.review.domain.ExternalSummary;
import com.simsasookbak.room.domain.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository  extends JpaRepository<Accommodation, Long> {


    @Query("SELECT a FROM Accommodation a WHERE a.region LIKE %:keyword% OR a.name LIKE %:keyword%")
    List<Accommodation> findKeyword(String keyword);

    Accommodation findAccommodationById(Long id);

    @Query("SELECT a FROM Room a WHERE a.accommodation.id = :acom_id")
    List<Room> findRoomByAcomId(@Param("acom_id") Long acom_id);

    @Query("SELECT a.summary FROM ExternalSummary a WHERE a.accommodation.id = :acom_id")
    String findExSummaryByAcomId(@Param("acom_id") Long acom_id);

    @Query("SELECT a.summary FROM InternalSummary a WHERE a.accommodation.id = :acom_id")
    String findInSummaryByAcomId(@Param("acom_id") Long acom_id);

    @Query("SELECT a.url FROM AccommodationImage a WHERE a.accommodation.id = :acom_id")
    List<String> findImgByAcomId(@Param("acom_id") Long acom_id);
}