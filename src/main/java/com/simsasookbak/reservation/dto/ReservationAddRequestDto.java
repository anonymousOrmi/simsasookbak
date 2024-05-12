package com.simsasookbak.reservation.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.room.domain.Room;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationAddRequestDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private String request;

    //TODO : Member 추가
    public Reservation toEntity(Member member, Accommodation accommodation, Room room) {
        return Reservation.builder().member(member).accommodation(accommodation).room(room).status("대기").startDate(startDate)
                .endDate(endDate).request(request).build();
    }

}
