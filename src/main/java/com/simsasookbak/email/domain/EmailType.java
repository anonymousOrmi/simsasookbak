package com.simsasookbak.email.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailType {
    CANCEL_USER_RESERVATION,
    CANCEL_ADMIN_RESERVATION,
    RESERVATION_APPROVAL;
}
