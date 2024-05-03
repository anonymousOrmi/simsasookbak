package com.simsasookbak.reservation.repository;

import com.simsasookbak.reservation.domain.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.status <> '완료' AND (r.startDate <= :endDate AND r.endDate >= :startDate)")
    List<Reservation> findNotCompleteStatus(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT r FROM Reservation r WHERE r.room.id = :roomId AND r.status = '완료'")
    List<Reservation> findAllByRoomId(@Param("roomId") Long roomId);

}
