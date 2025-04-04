package com.tsinsi.user.infrastructure.adapter;

import com.tsinsi.user.application.out.UserPersistencePort;
import com.tsinsi.user.domain.persistence.sql.UserEntity;
import com.tsinsi.user.domain.persistence.sql.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPersistenceAdapter implements UserPersistencePort {

    private final UserRepository userRepository;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserEntity> findBefore(long id) {
        return userRepository.findBefore(id);
    }

    @Override
    public List<UserEntity> findAfter(long id) {
        return userRepository.findAfter(id);
    }

}
