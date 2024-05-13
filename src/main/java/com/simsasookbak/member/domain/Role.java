package com.simsasookbak.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    BUSINESS_PERSON("ROLE_BUSINESS"),
    LEAVER("ROLE_LEAVER");

    private final String name;
}
