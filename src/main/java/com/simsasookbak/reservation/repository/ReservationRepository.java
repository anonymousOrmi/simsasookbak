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

    @Query("select re.id as id, "
            + "re.accommodation.id as accommodationId, "
            + "re.room.id as roomId, "
            + "re.status as status, "
            + "timestamp(re.startDate || ' ' || re.accommodation.checkIn) as checkinDate, "
            + "timestamp(re.endDate || ' ' || re.accommodation.checkOut) as checkoutDate, "
            + "re.createdAt as createdAt, "
            + "re.updatedAt as updatedAt "
            + "from Reservation re "
            + "join Accommodation accommodation on re.accommodation.id = accommodation.id "
            + "where re.accommodation.isDeleted = false "
            + "and re.status <> :status "
            + "and timestamp(re.endDate || ' ' || re.accommodation.checkOut) < current_timestamp "
    )
    List<ReservationView> findAllReservationByCurrentDate(@Param("status") String status);

    @Modifying(clearAutomatically = true)
    @Query("update Reservation set status = :status where id in (:ids)")
    int updateAllByReservationStatus(
            @Param("ids") List<Long> ids,
            @Param("status") String status
    );

    @Query("Select r from Reservation r Where r.member.id = :userId")
    List<Reservation> findAllReservationByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Reservation r SET r.status = '취소' WHERE r.id = :reservationId AND r.status IN ('완료', '대기')")
    void cancelReservationById(@Param("reservationId") Long reservationId);

    @Query("select reservation "
            + "from Reservation reservation "
            + "join fetch reservation.member "
            + "join fetch reservation.accommodation "
            + "join fetch reservation.room "
            + "where reservation.id = :id ")
    Reservation findReservationById(@Param("id") Long id);

}
