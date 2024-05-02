package com.simsasookbak.room.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.room.domain.Room;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(Long roomId);

    @Query("SELECT a FROM Room a WHERE a.accommodation.id = :acom_id")
    List<Room> findRoomsByAcomId(@Param("acom_id") Long acom_id);
}
