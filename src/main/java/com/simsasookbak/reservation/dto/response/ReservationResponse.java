package com.simsasookbak.reservation.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReservationResponse implements ReservationView {
    private String region;

    @Builder
    public ReservationResponse(
        String region
    ) {
        this.region = region;
    }
}
