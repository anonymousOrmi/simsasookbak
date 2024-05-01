package com.simsasookbak.accommodation.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.util.ArrayList;
import java.util.Collections;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter

public class AccommodationDto {

    private final String name;

    private final String content;

    private final String region;

    private final String checkIn;

    @Builder
    public AccommodationDto(String name, String content, String region, String checkIn, String checkOut,
                            Boolean isDeleted) {
        this.name = name;
        this.content = content;
        this.region = region;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isDeleted = isDeleted;
    }

    private final String checkOut;

    private final Boolean isDeleted;

    /**
     * Dto -> Entity
     */
    public static Accommodation toAccommodation(AccommodationDto dto) {
        return Accommodation.builder()
                .name(dto.getName())
                .content(dto.getContent())
                .region(dto.getRegion())
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .isDeleted(dto.getIsDeleted())
                .build();
    }

    /**
     * Entity -> Dto
     */
    public static AccommodationDto toAccommodationDto(Accommodation accommodation) {
        return AccommodationDto.builder()
                .name(accommodation.getName())
                .content(accommodation.getContent())
                .region(accommodation.getRegion())
                .checkIn(accommodation.getCheckIn())
                .checkOut(accommodation.getCheckOut())
                .isDeleted(accommodation.getIsDeleted())
                .build();
    }

}
