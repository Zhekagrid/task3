package com.hrydziushka.task3.exception;

public class CustomTextException extends Exception {
    public CustomTextException() {
    }

    public CustomTextException(String message) {
        super(message);
    }

    public CustomTextException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomTextException(Throwable cause) {
        super(cause);
    }
}
