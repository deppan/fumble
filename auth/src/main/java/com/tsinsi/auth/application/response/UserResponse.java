package com.tsinsi.auth.application.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    @JsonIgnore
    private long id;
    private String username;
    private String nickname;
    private int gender;

    public UserResponse(long id, String username, String nickname, int gender) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
    }
}
