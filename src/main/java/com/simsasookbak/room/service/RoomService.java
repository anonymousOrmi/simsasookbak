package com.simsasookbak.room.service;

import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.repository.RoomRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomDto findRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        List<String> facilities = roomRepository.findRoomFacilityById(roomId);

        return RoomDto.toDto(room,facilities);
    }

    public List<RoomDto> findRoomByAcomId(Long id) {
        return roomRepository.findRoomsByAcomId(id)
                .stream()
                .map(room -> RoomDto.toDto(room,findRoomFacilityById(room.getId()))) // RoomService를 전달
                .collect(Collectors.toList());
    }

    public List<String> findRoomFacilityById(Long roomId) {
        return roomRepository.findRoomFacilityById(roomId);
    }

}
