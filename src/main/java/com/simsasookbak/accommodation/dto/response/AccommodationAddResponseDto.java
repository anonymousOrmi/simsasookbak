package com.simsasookbak.accommodation.dto.response;

import com.simsasookbak.accommodation.domain.Accommodation;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccommodationAddResponseDto {
    private Long accommodationId;
    private Long memberId;
    private String name;
    private String address;
    private LocalTime checkIn;
    private LocalTime checkOut;

    public AccommodationAddResponseDto(Accommodation accommodation) {
        this.accommodationId = accommodation.getId();
        this.memberId = accommodation.getMember().getId();
        this.name = accommodation.getName();
        this.address = accommodation.getAddress();
        this.checkIn = accommodation.getCheckIn();
        this.checkOut = accommodation.getCheckOut();

    }
}
