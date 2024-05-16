package com.simsasookbak.accommodation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationResponse implements AccommodationView {
    private Long accommodationId;
    private String name;
    private String address;
    private String region;
    private Integer cost;
    private Double score;
    private String imageUrl;

    public AccommodationResponse(AccommodationView view) {
        this.accommodationId = view.getAccommodationId();
        this.name = view.getName();
        this.address = view.getAddress();
        this.region = view.getRegion();
        this.cost = view.getCost();
        this.score = view.getScore();
        this.imageUrl = view.getImageUrl();
    }

    public void roundScore() {
        this.score = Math.round(this.score * 10.0) / 10.0;
    }
}
