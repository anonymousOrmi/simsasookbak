package com.simsasookbak.room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RoomAvailabilityDto {
    private Long roomId;
    private boolean isDeleted;
    private Long memberId;
}
