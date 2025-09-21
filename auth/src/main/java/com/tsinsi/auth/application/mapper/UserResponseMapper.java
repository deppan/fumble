package com.tsinsi.auth.application.mapper;

import com.tsinsi.auth.application.response.UserResponse;
import com.tsinsi.auth.domain.User;

public class UserResponseMapper {
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getNickname(), user.getGender());
    }
}
