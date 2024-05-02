package com.simsasookbak.room.repository;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.room.domain.Room;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {


}
