package com.simsasookbak.room.repository;

import com.simsasookbak.room.domain.Room;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findById(Long roomId);

}
