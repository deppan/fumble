package com.tsinsi.user.application.port.in;

import com.tsinsi.user.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAfterAccounts(long afterId);

    List<User> findBeforeAccounts(long beforeId);

    User findOne(String username);
}
