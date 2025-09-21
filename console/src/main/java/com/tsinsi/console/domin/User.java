package com.tsinsi.console.domin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long uid;
    private String username;
    private String password;
    private String nickname;
    private int gender;
}
