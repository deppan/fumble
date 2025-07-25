package com.tsinsi.auth.application.out;

import com.tsinsi.auth.domain.model.User;

import java.util.Optional;

public interface UserPersistencePort {

    User save(User user);

    Optional<User> findByUsername(String username);
}
