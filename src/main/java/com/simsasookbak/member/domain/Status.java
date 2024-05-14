package com.simsasookbak.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {
    GENERAL("일반"),
    LEAVE("탈퇴");
    private final String state;
}
