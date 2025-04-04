package com.tsinsi.auth.application.out;

import com.tsinsi.auth.domain.persistence.UserEntity;

public interface UserPersistencePort {

    UserEntity save(UserEntity user);

    UserEntity findByUsername(String username);
}
