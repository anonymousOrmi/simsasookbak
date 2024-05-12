package com.simsasookbak.reservation.controller;

import com.simsasookbak.reservation.service.ReservationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationService reservationService;

    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId){
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok().build();
    }

//    @PutMapping("/renew/{reservationId}")
//    public ResponseEntity<Void> updateReservation(@PathVariable Long reservationId){
//        reservationService.updateReservation(reservationId);
//
//
//        return ResponseEntity.ok().build();
//    }
}
