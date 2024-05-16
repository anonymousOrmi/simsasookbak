package com.simsasookbak.external.youtube.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
