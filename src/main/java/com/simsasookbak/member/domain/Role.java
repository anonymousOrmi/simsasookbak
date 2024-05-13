package com.simsasookbak.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    BUSINESS("ROLE_BUSINESS");

    private final String name;
}
