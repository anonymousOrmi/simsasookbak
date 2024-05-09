package com.simsasookbak.reservation.dto.response;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.room.domain.Room;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReservationResponseDto {
    private Accommodation accommodation;
    private Member loginMember;
    private Room room;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String request;

    public ReservationResponseDto(Reservation reservation) {
        this.accommodation = reservation.getAccommodation();
        this.room = reservation.getRoom();
        this.status = reservation.getStatus();
        this.startDate = reservation.getStartDate();
        this.endDate = reservation.getEndDate();
        this.request = reservation.getRequest();
    }
}
