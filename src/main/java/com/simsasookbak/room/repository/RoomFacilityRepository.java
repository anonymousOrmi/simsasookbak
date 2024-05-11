package com.simsasookbak.room.repository;

import com.simsasookbak.room.domain.RoomFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityRepository extends JpaRepository<RoomFacility, Long> {
}
