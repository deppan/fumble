package com.tsinsi.user.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private long id;
    private String username;
    private String nickname;
    private int gender;
}
