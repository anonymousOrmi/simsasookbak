package com.simsasookbak.room.service;

import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow();
    }

}
