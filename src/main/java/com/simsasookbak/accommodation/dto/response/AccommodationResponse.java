package com.simsasookbak.accommodation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationResponse {
    private Long id;
    private String name;
    private String content;
    private String region;
    private String address;
    private String checkIn;
    private String checkOut;
    private Boolean isDeleted;
}
