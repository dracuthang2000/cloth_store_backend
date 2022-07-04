package com.ptithcm.clothing_store.security.jwt;

public class JWTResponse {
    private String accessToken;

    public JWTResponse(String token) {
        this.accessToken = token;
    }

    public JWTResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }
}
