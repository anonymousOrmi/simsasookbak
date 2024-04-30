package com.simsasookbak.room.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RoomFacilityMappingId implements Serializable {
    @Column(name = "room_id")
    private String roomId;
    @Column(name = "room_facility_id")
    private String roomFacilityId;
}
