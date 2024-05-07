package com.simsasookbak.accommodation.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AccommodationDto {

    private final Long id;

    private final String name;

    private final String content;

    private final String region;

    private final String address;

    private final String checkIn;

    private final String checkOut;

    private final Boolean isDeleted;

    private final List<String> facilityList;

    @Builder
    public AccommodationDto(Long id, String name, String content, String region, String address, String checkIn, String checkOut,
                            Boolean isDeleted, List<String> facilityList) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.region = region;
        this.address = address;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.isDeleted = isDeleted;
        this.facilityList = facilityList;

    }


    /**
     * Dto -> Entity
     */
    public static Accommodation toAccommodation(AccommodationDto dto) {
        return Accommodation.builder()
                .id(dto.getId())
                .name(dto.getName())
                .content(dto.getContent())
                .region(dto.getRegion())
                .address(dto.getAddress())   //? 이 부분만 에러..?
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .isDeleted(dto.getIsDeleted())
                .build();
    }

    /**
     * Entity -> Dto
     */
    public static AccommodationDto toAccommodationDto(Accommodation accommodation, List<String> facilityList) {
        return AccommodationDto.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .content(accommodation.getContent())
                .region(accommodation.getRegion())
                .address(accommodation.getAddress())
                .checkIn(String.valueOf(accommodation.getCheckIn()))
                .checkOut(String.valueOf(accommodation.getCheckOut()))
                .isDeleted(accommodation.getIsDeleted())
                .facilityList(facilityList)
                .build();
    }

}
