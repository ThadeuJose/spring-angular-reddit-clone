package com.example.springredditclone.dto;

import javax.validation.constraints.NotBlank;

public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;
    private String username;

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUsername() {
        return username;
    }

}
