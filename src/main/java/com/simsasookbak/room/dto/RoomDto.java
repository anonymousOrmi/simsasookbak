package com.simsasookbak.room.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.room.domain.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomDto {

    private Long roomId;
    private Long accommodationId;
    private String name;
    private Integer cost;
    private String content;
    private String useGuide;

    public RoomDto(Room room) {
        this.roomId = room.getId();
        this.accommodationId = room.getAccommodation().getId();
        this.name = room.getName();
        this.cost = room.getCost();
        this.content = room.getContent();
        this.useGuide = room.getUseGuide();
    }

    public Room toEntity(Accommodation accommodation) {
        return Room.builder().id(roomId).accommodation(accommodation).name(name).cost(cost).content(content)
                .useGuide(useGuide).build();
    }

}