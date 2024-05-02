package com.simsasookbak.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum ErrorMessage {
    NOT_EXIST_ITEM("데이터가 존재하지 않습니다.");

    private String message;
}
