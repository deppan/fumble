package com.tsinsi.user.application.mapper;

import com.tsinsi.user.application.response.UserResponse;
import com.tsinsi.user.domain.model.User;

public class UserResponseMapper {
    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getNickname(), user.getGender());
    }
}
