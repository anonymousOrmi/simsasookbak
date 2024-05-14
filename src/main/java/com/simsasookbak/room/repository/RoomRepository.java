package com.simsasookbak.room.repository;

import com.simsasookbak.room.domain.Room;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByIdAndIsDeletedFalse(Long roomId);

    List<Room> findRoomByAccommodationId(Long accommodationId);

    @Query("SELECT a.roomFacility.name FROM RoomFacilityMapping a WHERE a.room.id = :id")
    List<String> findRoomFacilityById(Long id);
}
