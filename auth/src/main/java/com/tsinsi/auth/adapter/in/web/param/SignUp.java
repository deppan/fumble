package com.tsinsi.auth.adapter.in.web.param;

import com.tsinsi.auth.entity.User;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUp extends SignIn {

    @NotBlank(message = "{nickname}")
    @Size(min = 4, max = 12, message = "{nickname.length}")
    private String nickname;

    @Max(value = 2, message = "{gender}")
    @Min(value = 0, message = "{gender}")
    private Integer gender;

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);
        user.setGender(gender == null ? 0 : gender);
        return user;
    }
}