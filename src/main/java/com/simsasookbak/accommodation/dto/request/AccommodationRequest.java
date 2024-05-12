package com.simsasookbak.accommodation.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class AccommodationRequest {
    private String keyword;
    private String startDate;
    private String endDate;

    public boolean isEmptyAllDates() {
        return StringUtils.isBlank(startDate) && StringUtils.isBlank(endDate);
    }
}
