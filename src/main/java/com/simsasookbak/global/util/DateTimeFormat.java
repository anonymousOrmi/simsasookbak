package com.simsasookbak.global.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateTimeFormat {
    DATE("yyyy-MM-dd"),
    TIME("HH:mm:ss"),
    DATE_TIME("yyyy-MM-dd HH:mm:ss");

    private final String format;
}
