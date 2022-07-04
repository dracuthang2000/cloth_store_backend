package com.ptithcm.clothing_store.model.exception;

public class UsernameNotFoundException extends RuntimeException {
    private static final long serialVersionUID =1l;

    public UsernameNotFoundException(String message) {
        super(message);
    }
}
