package com.simsasookbak.reservation.controller;

import com.simsasookbak.email.domain.MailType;
import com.simsasookbak.email.service.MailService;
import com.simsasookbak.reservation.service.ReservationService;
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
    private final MailService mailService;


    @PutMapping("/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long reservationId) throws Exception {
        reservationService.cancelReservation(reservationId);
        mailService.sendMail(MailType.RESERVATION_CANCEL, reservationId);
        return ResponseEntity.ok().build();
    }
}
