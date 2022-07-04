package com.ptithcm.clothing_store.model.exception;

public class PasswordWrongException extends RuntimeException {
    private static final long serialVersionUID =1l;

    public PasswordWrongException(String message) {
        super(message);
    }
}
