package com.simsasookbak.room.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.room.domain.Room;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoomDto {

    private Long roomId;
    private Long memberId;
    private Long accommodationId;
    private String name;
    private Integer cost;
    private String content;
    private String useGuide;
    private List<String> facilityList;
    private boolean isDeleted;

    public static RoomDto toDto(Room room, List<String> facilityList) {
        return RoomDto.builder()
                .roomId(room.getId())
                .memberId(room.getAccommodation().getMember().getId())
                .accommodationId(room.getAccommodation().getId())
                .name(room.getName())
                .cost(room.getCost())
                .content(room.getContent())
                .useGuide(room.getUseGuide())
                .facilityList(facilityList)
                .isDeleted(room.getIsDeleted())
                .build();
    }

    public Room toEntity(Accommodation accommodation) {
        return Room.builder().id(roomId).accommodation(accommodation).name(name).cost(cost).content(content)
                .useGuide(useGuide).build();
    }
}