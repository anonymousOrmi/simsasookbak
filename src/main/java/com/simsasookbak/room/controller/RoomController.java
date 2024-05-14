package com.simsasookbak.room.controller;

import com.simsasookbak.global.aop.MethodInvocationLimit;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.dto.RoomUpdateDto;
import com.simsasookbak.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @MethodInvocationLimit
    @PutMapping("/room/{roomId}/roomUpdate")
    public ResponseEntity<RoomUpdateDto> updateRoom(@AuthenticationPrincipal Member member, @PathVariable Long roomId, @RequestBody
    RoomUpdateDto accommodationUpdateDto) {

        roomService.updateRoom(member, roomId, accommodationUpdateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationUpdateDto);
    }
}
