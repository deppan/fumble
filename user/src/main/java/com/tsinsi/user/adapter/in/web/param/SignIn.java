package com.tsinsi.user.adapter.in.web.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignIn {
    @NotEmpty(message = "{username}")
    private String username;
    @NotEmpty(message = "{password}")
    private String password;
}
