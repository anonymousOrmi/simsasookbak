package com.simsasookbak.room.service;

import com.simsasookbak.room.domain.RoomFacility;
import com.simsasookbak.room.repository.RoomFacilityRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomFacilityService {

    private final RoomFacilityRepository roomFacilityRepository;

    public List<RoomFacility> findAll() {
        return roomFacilityRepository.findAll();
    }

}
