package com.simsasookbak.reservation.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.reservation.domain.Reservation;
import com.simsasookbak.room.domain.Room;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationAddRequestDto {

    private Date startDate;
    private Date endDate;
    private String request;

    //TODO : Member 추가
    public Reservation toEntity(Accommodation accommodation, Room room) {
        return Reservation.builder().accommodation(accommodation).room(room).status("대기").startDate(startDate)
                .endDate(endDate).request(request).build();
    }

}
