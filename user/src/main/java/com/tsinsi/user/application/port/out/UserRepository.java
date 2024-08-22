package com.tsinsi.user.application.port.out;

import com.tsinsi.user.entity.User;

import java.util.List;

public interface UserRepository {

    List<User> findBefore(long id);

    List<User> findAfter(long id);

    User findByUsername(String username);
}
