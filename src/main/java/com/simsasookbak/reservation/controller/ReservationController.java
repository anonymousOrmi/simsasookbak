package com.simsasookbak.reservation.controller;

import com.simsasookbak.reservation.dto.ReservationRequestDto;
import com.simsasookbak.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/{accommodationId}/{roomId}/reservationPage")
    @ResponseBody
    public ResponseEntity<?> saveReservation(@PathVariable Long accommodationId, @PathVariable Long roomId, @RequestBody
                                             ReservationRequestDto requestDto) {
        return null;
    }

}
