package com.regular.fileextensionblocker.exception;

public abstract class PolicyException extends RuntimeException {

    protected PolicyException(String message) {
        super(message);
    }
}
