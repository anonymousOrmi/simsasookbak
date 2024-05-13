package com.simsasookbak.room.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoomUpdateDto {

    private String name;
    private Integer cost;
    private String content;
    private String useGuide;
    private List<String> facilityList;

}
