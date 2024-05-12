package com.simsasookbak.room.service;

import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.dto.RoomUpdateDto;
import com.simsasookbak.room.repository.RoomRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomFacilityMappingService roomFacilityMappingService;

    // TODO: NOTFOUNDEXCEPTION 커스텀하기
    public RoomDto findRoomById(Long roomId) {
//<<<<<<< HEAD
        Room room = roomRepository.findByIdAndIsDeletedFalse(roomId).orElseThrow();
        List<String> facilities = roomRepository.findRoomFacilityById(roomId);

        return RoomDto.toDto(room,facilities);
//=======
//        return roomRepository.findByIdAndIsDeletedFalse(roomId)
//                .map(RoomDto::new)
//                .orElseThrow();
//>>>>>>> develop
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

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void updateRoom(Long roomId, RoomUpdateDto roomUpdateDto) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.update(roomUpdateDto);
        List<String> roomFacilityList = roomUpdateDto.getFacilityList();

        roomFacilityMappingService.deleteMapping(roomId);

        roomFacilityMappingService.registerMapping(room, roomFacilityList);
    }

}
