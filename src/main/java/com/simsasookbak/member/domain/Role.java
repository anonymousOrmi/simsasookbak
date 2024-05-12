package com.simsasookbak.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("관리자"),
    USER("이용자"),
    BUSINESS_PERSON("사업자");

    private final String name;
}
