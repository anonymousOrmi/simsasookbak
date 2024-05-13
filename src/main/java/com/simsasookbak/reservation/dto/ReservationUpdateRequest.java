package com.simsasookbak.reservation.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationUpdateRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private String requestMessage;
}
