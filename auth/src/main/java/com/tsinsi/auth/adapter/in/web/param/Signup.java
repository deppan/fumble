package com.tsinsi.auth.adapter.in.web.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Signup {
    @NotEmpty(message = "{username}")
    private String username;
    @NotEmpty(message = "{password}")
    private String password;
    private String nickname;
    private int gender;
}
