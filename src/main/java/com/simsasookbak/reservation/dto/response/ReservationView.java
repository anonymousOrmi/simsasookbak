package com.simsasookbak.reservation.dto.response;

import java.time.LocalDateTime;

public interface ReservationView {
    Long getId();
    String getRegion();
    Long getAccommodationId();
    Long getRoomId();
    String getStatus();
    LocalDateTime getCheckinDate();
    LocalDateTime getCheckoutDate();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
}
