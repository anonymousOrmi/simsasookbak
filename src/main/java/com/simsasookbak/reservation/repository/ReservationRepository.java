package com.simsasookbak.reservation.repository;

import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.reservation.dto.response.ReservationView;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("select reservation.id as id, "
         + "reservation.accommodation.id as accommodationId, "
         + "reservation.room.id as roomId, "
         + "reservation.status as status, "
         + "timestamp(reservation.startDate || ' ' || reservation.accommodation.checkIn) as checkinDate, "
         + "timestamp(reservation.endDate || ' ' || reservation.accommodation.checkOut) as checkoutDate, "
         + "reservation.createdAt as createdAt, "
         + "reservation.updatedAt as updatedAt "
         + "from Reservation reservation "
         + "join Accommodation accommodation on reservation.accommodation.id = accommodation.id "
         + "where reservation.accommodation.isDeleted = false "
         + "and reservation.status <> '만료' "
         + "and timestamp(reservation.endDate || ' ' || reservation.accommodation.checkOut) < current_timestamp "
    )
    List<ReservationView> findAllReservationByCurrentDate();

    @Modifying(clearAutomatically = true)
    @Query("update Reservation set status = :status where id in (:ids)")
    int updateAllByReservationStatus(
            @Param("ids") List<Long> ids,
            @Param("status") String status
    );

}
