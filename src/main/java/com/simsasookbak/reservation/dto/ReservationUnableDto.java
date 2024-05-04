package com.simsasookbak.reservation.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationUnableDto {

    private List<String> checkInUnselectableDate = new ArrayList<>();
    private List<String> checkOutUnselectableDate = new ArrayList<>();

    public void addCheckInUnselectableDate(Date date) {
        checkInUnselectableDate.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

    public void addCheckOutUnselectableDate(Date date) {
        checkOutUnselectableDate.add(new SimpleDateFormat("yyyy-MM-dd").format(date));
    }

}
