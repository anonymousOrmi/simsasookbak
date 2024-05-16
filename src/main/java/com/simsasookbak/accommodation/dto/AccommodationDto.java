package com.simsasookbak.accommodation.dto;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.util.List;
import java.time.LocalTime;
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

    private final LocalTime checkIn;

    private final LocalTime checkOut;

    private final Boolean isDeleted;

    private final List<String> facilityList;

    private Double averageScore;

    private String imageUrl;


    @Builder
    public AccommodationDto(Long id, String name, String content, String region, String address, String checkIn, String checkOut,
                            Boolean isDeleted, List<String> facilityList, Double averageScore, String imageUrl) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.region = region;
        this.address = address;
        this.checkIn = LocalTime.parse(checkIn);
        this.checkOut = LocalTime.parse(checkOut);
        this.isDeleted = isDeleted;
        this.facilityList = facilityList;
        this.averageScore = averageScore;
        this.imageUrl = imageUrl;
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
                .address(dto.getAddress())
                .checkIn(String.valueOf(dto.getCheckIn()))
                .checkOut(String.valueOf(dto.getCheckOut()))
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

    public void setAverageScore(double averageScore) {
        //소수점 첫째자리까지만
        this.averageScore = Math.round(averageScore * 10.0) / 10.0;
    }

    public void setAverageScoreForOneAccommodation(double averageScore) {
        if(averageScore == 0.0) {
            this.averageScore = 0.0;
        } else {
            this.averageScore = Math.round(averageScore * 10.0) / 10.0;
        }
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
