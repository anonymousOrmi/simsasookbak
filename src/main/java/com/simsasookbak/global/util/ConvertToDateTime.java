package com.simsasookbak.global.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConvertToDateTime {
    public static LocalDate convertToLocalDate(String format) {
        String date = DateTimeFormat.DATE.getFormat();
        return LocalDate.parse(format, DateTimeFormatter.ofPattern(date));
    }
}
