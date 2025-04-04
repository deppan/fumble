package com.tsinsi.auth.infrastructure.adapter;

import com.tsinsi.auth.application.out.UserPersistencePort;
import com.tsinsi.auth.domain.persistence.UserEntity;
import com.tsinsi.auth.domain.persistence.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
