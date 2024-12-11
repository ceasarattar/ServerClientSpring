package com.example.springdemojdbc.exception;

public class CommandException extends RuntimeException {
    private final String errorCode;

    public CommandException(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
