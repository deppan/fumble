package com.tsinsi.console.application.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String username;
    private String nickname;
    private int gender;
    private String token;

    public AuthResponse(String username, String nickname, int gender, String token) {
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.token = token;
    }
}
