package com.simsasookbak.room.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.room.domain.Room;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomAddRequestDto {
    private String name;
    private Integer cost;
    private String content;
    private String useGuide;
    private List<String> facilityList;

    public Room toEntity(Accommodation accommodation) {

        return Room.builder()
                .accommodation(accommodation)
                .name(name)
                .cost(cost)
                .content(content)
                .useGuide(useGuide)
                .isDeleted(false)
                .build();
    }
}
