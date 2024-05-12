package com.simsasookbak.accommodation.dto.request;

import com.simsasookbak.room.dto.RoomAddRequestDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccommodationAndRoomsAddRequestDto {

    private AccommodationAddRequestDto accommodationAddRequestDto;
    private List<RoomAddRequestDto> roomAddRequestDtoList;

}
