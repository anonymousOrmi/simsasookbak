package com.simsasookbak.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    YOUTUBE_VIDEO_NOT_FOUND("Youtube 영상 데이터를 찾을 수 없습니다."),

    UNEXPECTED_ROW_COUNT("예상 ROW 반환과 상이한 ROW 수가 반환 되었습니다.");

    private final String message;
}
