package com.tsinsi.auth.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private long id;
    private String password;
    private String username;
    private String nickname;
    private int gender;

}
