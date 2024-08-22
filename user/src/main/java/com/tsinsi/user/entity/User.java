package com.tsinsi.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User extends Entity {
    @JsonIgnore
    private String password;
    private String username;
    private String nickname;
    private int gender;
}
