package com.tsinsi.auth.application.out;

import com.tsinsi.auth.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByUsername(String username);
}
