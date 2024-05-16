package com.simsasookbak.room.repository;

import com.simsasookbak.room.domain.RoomFacilityMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilityMappingRepository extends JpaRepository<RoomFacilityMapping, Long> {

    void deleteRoomFacilityMappingByRoomId(Long roomId);

}
