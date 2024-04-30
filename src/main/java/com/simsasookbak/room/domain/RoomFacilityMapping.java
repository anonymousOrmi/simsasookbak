/*
package com.simsasookbak.room.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
*/
/*@Entity*//*

@NoArgsConstructor
@AllArgsConstructor
public class RoomFacilityMapping {
    @Id
    @EmbeddedId
    RoomFacilityMappingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false, insertable = false, updatable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_facility_id", nullable = false, insertable = false, updatable = false)
    private RoomFacility roomFacilities;
}
*/
