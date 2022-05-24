package com.tsinsi.fumble.account.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    private Id id;
    private String username;
    private String password;
    private int gender;
}
