package com.example.springredditclone.dto;

import java.time.Instant;

public class AuthenticationResponse {
    private String authenticationToken;
    private String refreshToken;
    private Instant expireAt;
    private String username;

    public AuthenticationResponse(String authenticationToken, String refreshToken, Instant expireAt, String username) {
        this.authenticationToken = authenticationToken;
        this.refreshToken = refreshToken;
        this.expireAt = expireAt;
        this.username = username;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Instant getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Instant expireAt) {
        this.expireAt = expireAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
