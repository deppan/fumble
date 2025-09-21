package com.tsinsi.console.application.out;

import com.tsinsi.console.domin.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);

    Optional<User> findByUsername(String username);
}
