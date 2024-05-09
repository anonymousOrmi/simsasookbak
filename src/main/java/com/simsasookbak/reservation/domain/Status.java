package com.simsasookbak.reservation.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    WAIT("대기"),
    COMPLETE("완료"),
    EXPIRE("만료"),
    CANCEL("취소");

    private final String name;
}
