package com.simsasookbak.external.youtube.exception;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.simsasookbak.external.youtube.controller")
public class YoutubeExceptionHandler {
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException e) {
        return handleExceptionInternal(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return handleExceptionInternal(HttpStatus.NOT_FOUND, e.getMessage());
    }

    private ResponseEntity<?> handleExceptionInternal(
            final HttpStatus code,
            final String message
    ) {
        return ResponseEntity.status(code).body(message);
    }
}
