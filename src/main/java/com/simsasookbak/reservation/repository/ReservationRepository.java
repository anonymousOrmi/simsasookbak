package com.simsasookbak.reservation.repository;

import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.dto.response.ReservationView;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(
          "select reservation.accommodation.region as region "
        + "from Reservation reservation "
        + "join reservation.member member "
        + "join reservation.accommodation accommodation "
        + "where accommodation.isDeleted = false "
        + "and reservation.createdAt > :diffDatetime "
        + "group by region order by region desc "
    )
    List<ReservationView> findPopularRegionsByCreatedAt(
            @Param("diffDatetime") LocalDateTime diffDatetime,
            Pageable pageable
    );

    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND r.status = '완료'")
    List<Reservation> findAllCompleteStatusRoomByRoomId(@Param("roomId") Long roomId);
}
