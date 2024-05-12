package com.simsasookbak.room.controller;

import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{room_id}")
    public ResponseEntity<?> getRoom(@PathVariable Long room_id) {
        RoomDto room = roomService.findRoomById(room_id);
        return ResponseEntity.ok().body(room);
    }


}
