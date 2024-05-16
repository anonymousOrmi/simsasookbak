package com.simsasookbak.room.service;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomAvailabilityDto;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.dto.RoomUpdateDto;
import com.simsasookbak.room.repository.RoomRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomFacilityMappingService roomFacilityMappingService;

    public RoomDto findRoomById(Long roomId) {
        Room room = roomRepository.findByIdAndIsDeletedFalse(roomId).orElseThrow();
        List<String> facilities = roomRepository.findRoomFacilityById(roomId);

        return RoomDto.toDto(room, facilities);
    }

    public List<RoomDto> findRoomByAccommodationId(Long id) {
        return roomRepository.findRoomByAccommodationId(id)
                .stream()
                .map(room -> RoomDto.toDto(room, findRoomFacilityById(room.getId()))) // RoomService를 전달
                .collect(Collectors.toList());
    }

    public List<String> findRoomFacilityById(Long roomId) {
        return roomRepository.findRoomFacilityById(roomId);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void updateRoom(Member member, Long roomId, RoomUpdateDto roomUpdateDto) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        checkMemberValid(member, room);
        room.update(roomUpdateDto);
        List<String> roomFacilityList = roomUpdateDto.getFacilityList();

        roomFacilityMappingService.deleteMapping(roomId);

        roomFacilityMappingService.registerMapping(room, roomFacilityList);
    }

    public RoomAvailabilityDto manageRoomAvailability(Member member, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        checkMemberValid(member, room);
        int countSameAccommodationAvailableRooms = roomRepository.countRoomByAccommodationIdAndIsDeletedFalse(
                room.getAccommodation().getId());

        if (!room.getIsDeleted() && countSameAccommodationAvailableRooms > 1) {
            room.setIsDeleted(true);
        } else {
            room.setIsDeleted(false);
        }

        return RoomAvailabilityDto.builder().roomId(roomId)
                .isDeleted(room.getIsDeleted()).memberId(member.getId()).build();
    }

    private void checkMemberValid(Member member, Room room) {
        if (!Objects.equals(member.getId(), room.getAccommodation().getMember().getId())) {
            throw new AccessDeniedException("자신의 객실만 수정할 수 있습니다.");
        }
    }

    public List<Room> findRoomByAccommodationIdToDelete(Long accommodationId) {
        return roomRepository.findRoomByAccommodationId(accommodationId);
    }
}
