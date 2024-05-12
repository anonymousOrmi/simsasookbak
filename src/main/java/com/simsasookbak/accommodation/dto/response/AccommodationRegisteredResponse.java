package com.simsasookbak.accommodation.dto.response;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationRegisteredResponse {
    private Long accommodationId;
    private Long memberId;
    private String name;
    private String content;
    private String region;
    private String address;
    private LocalTime checkIn;
    private LocalTime checkOut;

    public AccommodationRegisteredResponse(Accommodation accommodation) {
        this.accommodationId = accommodation.getId();
        this.name = accommodation.getName();
        this.content = accommodation.getContent();
        this.region = accommodation.getRegion();
        this.address = accommodation.getAddress();
        this.checkIn = accommodation.getCheckIn();
        this.checkOut = accommodation.getCheckOut();
    }
}
