package com.simsasookbak.reservation.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularRegionRequest {
    private Integer day;
    private Integer limit;
}
