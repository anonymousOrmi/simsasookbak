package com.simsasookbak.reservation.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReservationRequestDto {

    private Long memberId;
    private Long accommodationId;
    private Long roomId;
    private String status;
    private Date startDate;
    private Date endDate;
    private String request;

}
