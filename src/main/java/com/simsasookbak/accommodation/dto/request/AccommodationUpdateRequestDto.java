package com.simsasookbak.accommodation.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccommodationUpdateRequestDto {
    private String name;
    private String content;
    private String region;
    private String address;
    private String checkIn;
    private String checkOut;
    private List<String> facilityList;
}
