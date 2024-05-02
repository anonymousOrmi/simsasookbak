package com.simsasookbak.accommodation.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.util.Date;
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


    //startDate가 endDate보다 이른 경우 1, 같으면 0 늦으면 -1
    @Query("SELECT " +
            "CASE " +
            "WHEN DATEDIFF(:startDate, :endDate) < 0 THEN 1 " +
            "WHEN DATEDIFF(:startDate, :endDate) = 0 THEN 0 " +
            "ELSE -1 " +
            "END " +
            "FROM Accommodation a")
    int compareStartAndEndDates(Date startDate, Date endDate);

//    @Query("SELECT a FROM Accommodation a WHERE a.id NOT IN " +
//            "(SELECT res.accommodation.id FROM Reservation res WHERE res.startDate <= :endDate AND res.endDate >= :startDate) " +
//            "OR a.id NOT IN (SELECT r.accommodation.id FROM Room r)")
//    List<Accommodation> findAvailableAccommodationsByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT DISTINCT a FROM Accommodation a " +
            "LEFT JOIN Room r ON r.accommodation = a " +
            "WHERE (a.id NOT IN (SELECT res.accommodation.id FROM Reservation res WHERE res.startDate <= :endDate AND res.endDate >= :startDate) " +
            "OR r.id NOT IN (SELECT res.room.id FROM Reservation res WHERE res.room.accommodation = a AND res.startDate <= :endDate AND res.endDate >= :startDate))")
    List<Accommodation> findAvailableAccommodationsByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
    Accommodation findAccommodationById(Long id);

//    @Query("SELECT a.url FROM AccommodationImage a WHERE a.accommodation.id = :acom_id")
//    List<String> findImgByAcomId(@Param("acom_id") Long acom_id);
}
