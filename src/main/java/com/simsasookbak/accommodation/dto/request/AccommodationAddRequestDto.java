package com.simsasookbak.accommodation.dto.request;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.domain.AccommodationFacility;
import com.simsasookbak.member.domain.Member;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccommodationAddRequestDto {
    private String name;
    private String content;
    private String region;
    private String address;
    private String checkIn;
    private String checkOut;
    private List<String> facilityList;

    public Accommodation toEntity(Member member) {
        return Accommodation.builder()
                .member(member)
                .name(name)
                .content(content)
                .region(region)
                .address(address).checkIn(checkIn).checkOut(checkOut).isDeleted(false).build();
    }
}
