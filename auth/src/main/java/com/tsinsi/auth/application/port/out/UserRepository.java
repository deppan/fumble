package com.tsinsi.auth.application.port.out;

import com.tsinsi.auth.entity.User;

public interface UserRepository {

    User signup(User user);

    User findByUsername(String username);
}
