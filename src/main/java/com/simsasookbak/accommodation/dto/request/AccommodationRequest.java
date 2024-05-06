package com.simsasookbak.accommodation.dto.request;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccommodationRequest {
    private String keyword;
    private LocalDate startDate;
    private LocalDate endDate;

    public boolean isEmptyAllDates() {
        return startDate == null && endDate == null;
    }
}
