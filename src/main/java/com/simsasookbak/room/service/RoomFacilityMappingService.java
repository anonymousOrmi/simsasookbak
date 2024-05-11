package com.simsasookbak.room.service;

import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.domain.RoomFacility;
import com.simsasookbak.room.domain.RoomFacilityMapping;
import com.simsasookbak.room.repository.RoomFacilityMappingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomFacilityMappingService {

    private final RoomFacilityService roomFacilityService;
    private final RoomFacilityMappingRepository roomFacilityMappingRepository;

    public void registerMapping(Room room, List<String> facilityList) {
        for(String facility : facilityList) {

            RoomFacility roomFacility = roomFacilityService.findByName(facility);
            RoomFacilityMapping roomFacilityMapping = new RoomFacilityMapping(room, roomFacility);

            roomFacilityMappingRepository.save(roomFacilityMapping);
        }
    }

}
