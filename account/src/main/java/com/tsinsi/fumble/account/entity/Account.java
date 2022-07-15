package com.tsinsi.fumble.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account extends Entity {
    @JsonIgnore
    private String password;
    private String username;
    private int gender;
}
