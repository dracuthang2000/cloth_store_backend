package com.ptithcm.clothing_store.model.exception;

public class NotAcceptableHandleException extends RuntimeException {
    private static final long serialVersionUID =1l;
    public NotAcceptableHandleException(String message) {
        super(message);
    }
}
