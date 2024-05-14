package com.simsasookbak.reservation.controller;

import com.simsasookbak.email.domain.MailType;
import com.simsasookbak.email.service.MailService;
import com.simsasookbak.reservation.dto.ReservationUpdateRequest;
import com.simsasookbak.reservation.service.ReservationService;
import jakarta.mail.MessagingException;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PutMapping("/renew/{reservationId}")
    public ResponseEntity<Void> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationUpdateRequest data){
        LocalDate startDate = data.getStartDate();
        LocalDate endDate = data.getEndDate();
        String requestMessage = data.getRequestMessage();

        reservationService.updateReservation(reservationId, startDate, endDate, requestMessage);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/approve/{reservationId}")
    public ResponseEntity<Void> approveReservation(@PathVariable Long reservationId) throws MessagingException {
        reservationService.approveReservation(reservationId);
        mailService.sendMail(MailType.RESERVATION_APPROVAL, reservationId);
        return ResponseEntity.ok().build();
    }
}
