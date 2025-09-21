package com.tsinsi.user.application.in;

import com.tsinsi.user.application.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findUsers(long beforeId, long afterId);

    UserResponse findOne(String username);

}
