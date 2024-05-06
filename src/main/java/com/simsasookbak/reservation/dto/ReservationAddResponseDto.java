package com.simsasookbak.reservation.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.room.domain.Room;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReservationAddResponseDto {

    //TODO : 회원
//    private Long memberId;
    private Long accommodationId;
    private Long roomId;
    private String status;
    private Date startDate;
    private Date endDate;
    private String request;

    public ReservationAddResponseDto(Reservation reservation) {
        this.accommodationId = reservation.getAccommodation().getId();
        this.roomId = reservation.getRoom().getId();
        this.status = reservation.getStatus();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.request = reservation.getRequest();
    }

}
