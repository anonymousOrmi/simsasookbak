package com.simsasookbak.room.repository;

import com.simsasookbak.room.domain.Room;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(Long roomId);

    @Query("SELECT a FROM Room a WHERE a.accommodation.id = :acom_id")
    List<Room> findRoomsByAcomId(@Param("acom_id") Long acom_id);

    @Query("SELECT a.roomFacility.name FROM RoomFacilityMapping a WHERE a.room.id = :id")
    List<String> findRoomFacilityById(Long id);
}
