package com.simsasookbak.global.exception;


public class MethodInvocationLimitException extends RuntimeException{
    public MethodInvocationLimitException(String message) {
        super(message);
    }

    public MethodInvocationLimitException(String message, Throwable cause) {
        super(message, cause);
    }
}
