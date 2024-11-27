package com.tsinsi.auth.adapter.in.web.param;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignIn {
    @NotBlank(message = "{username}")
    @Size(min = 4, max = 12, message = "{username.length}")
    protected String username;

    @NotBlank(message = "{password}")
    @Size(min = 6, max = 16, message = "{password.length}")
    protected String password;
}
