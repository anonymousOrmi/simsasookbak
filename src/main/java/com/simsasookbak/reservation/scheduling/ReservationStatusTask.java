package com.simsasookbak.reservation.scheduling;

import com.simsasookbak.reservation.domain.Status;
import com.simsasookbak.reservation.dto.response.ReservationResponse;
import com.simsasookbak.reservation.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationStatusTask {
    private final ReservationService reservationService;

    @Value("${spring.schedule.use}")
    private boolean isEnabled;

    @Scheduled(cron = "${spring.schedule.cron}")
    public void run() throws Exception {
        if(isEnabled) {
            List<ReservationResponse> expiredReservations = reservationService.getExpiredReservations();
            reservationService.updateAllReservation(expiredReservations, Status.EXPIRE.getName());
            return;
        }
        throw new InterruptedException();
    }
}
