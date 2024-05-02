package com.simsasookbak.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum ErrorMessage {
    YOUTUBE_VIDEO_NOT_FOUND("Youtube 영상 데이터를 찾을 수 없습니다.");

    private String message;
}
