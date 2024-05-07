package com.simsasookbak.reservation.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReservationUnableDto {

    private List<String> checkInUnselectableDate = new ArrayList<>();
    private List<String> checkOutUnselectableDate = new ArrayList<>();

    public void addCheckInUnselectableDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        checkInUnselectableDate.add(date.format(formatter));
    }

    public void addCheckOutUnselectableDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        checkOutUnselectableDate.add(date.format(formatter));
    }

}
