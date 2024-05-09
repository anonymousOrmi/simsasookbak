package com.simsasookbak.reservation.dto.response;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationResponse implements ReservationView {
    private Long id;
    private String region;
    private Long accommodationId;
    private Long roomId;
    private String status;
    private LocalDateTime checkinDate;
    private LocalDateTime checkoutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ReservationResponse(String region) {
        this.region = region;
    }

    public ReservationResponse(ReservationView view) {
        this.id = view.getId();
        this.accommodationId = view.getAccommodationId();
        this.roomId = view.getRoomId();
        this.status = view.getStatus();
        this.checkinDate = getCheckinDate();
        this.checkoutDate = getCheckoutDate();
        this.createdAt = getCreatedAt();
        this.updatedAt = getUpdatedAt();
    }
}
