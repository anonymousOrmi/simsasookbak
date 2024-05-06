package com.simsasookbak.reservation.controller;

import com.simsasookbak.reservation.dto.ReservationAddRequestDto;
import com.simsasookbak.reservation.dto.ReservationAddResponseDto;
import com.simsasookbak.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    //TODO: 회원정보 받아오기
    @PostMapping("accommodation/{accommodationId}/{roomId}/reservationPage")
    public ResponseEntity<ReservationAddResponseDto> saveReservation(@PathVariable Long accommodationId,
                                                                     @PathVariable Long roomId,
                                                                     @RequestBody ReservationAddRequestDto request) {

        ReservationAddResponseDto responseDto = reservationService.save(accommodationId, roomId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
