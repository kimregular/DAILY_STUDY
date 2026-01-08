package com.regular.fileextensionblocker.exception;

public class CustomLimitExceededException extends PolicyException {
    public CustomLimitExceededException(String message) {
        super(message);
    }
}
