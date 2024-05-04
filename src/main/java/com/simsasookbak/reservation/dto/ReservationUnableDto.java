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

    private String[] checkInUnselectableDate;
    private String[] checkOutUnselectableDate;

    public void addCheckInUnselectableDate(Date date) {
        // checkInUnselectableDate 배열의 길이를 1 증가시켜 새로운 날짜를 추가
        int length = checkInUnselectableDate != null ? checkInUnselectableDate.length : 0;
        String[] newArr = new String[length + 1];
        if (length > 0) {
            System.arraycopy(checkInUnselectableDate, 0, newArr, 0, length);
        }
        newArr[length] = new SimpleDateFormat("yyyy-MM-dd").format(date);
        checkInUnselectableDate = newArr;
    }

    public void addCheckOutUnselectableDate(Date date) {
        // checkOutUnselectableDate 배열의 길이를 1 증가시켜 새로운 날짜를 추가
        int length = checkOutUnselectableDate != null ? checkOutUnselectableDate.length : 0;
        String[] newArr = new String[length + 1];
        if (length > 0) {
            System.arraycopy(checkOutUnselectableDate, 0, newArr, 0, length);
        }
        newArr[length] = new SimpleDateFormat("yyyy-MM-dd").format(date);
        checkOutUnselectableDate = newArr;
    }

    public String[] getCheckInUnselectableDate() {
        return checkInUnselectableDate;
    }

    public String[] getCheckOutUnselectableDate() {
        return checkOutUnselectableDate;
    }

}
