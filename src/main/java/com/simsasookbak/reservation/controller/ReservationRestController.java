package com.simsasookbak.reservation.controller;

import com.simsasookbak.reservation.service.ReservationService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservations")
public class ReservationRestController {

    private final ReservationService reservationService;

//    @Autowired
//    public ReservationRestController(ReservationService reservationService) {
//        this.reservationService = reservationService;
//    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId){
        Optional<Long> optionalReservationId = Optional.of(reservationId);
        reservationService.cancelReservation(optionalReservationId);
        return ResponseEntity.ok().build();
    }
}
