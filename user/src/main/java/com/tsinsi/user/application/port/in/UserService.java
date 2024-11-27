package com.tsinsi.user.application.port.in;

import com.tsinsi.user.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAfterUsers(long afterId);

    List<User> findBeforeUsers(long beforeId);

    User findOne(String username);
}
