package com.simsasookbak.room.service;

import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomDto findRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();

        return RoomDto.toDto(room);
    }

}
