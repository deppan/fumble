package com.tsinsi.auth.adapter.out.persistence.repository;

import com.tsinsi.auth.adapter.out.persistence.entity.UserEntity;
import com.tsinsi.auth.adapter.out.persistence.mapper.UserMapper;
import com.tsinsi.auth.application.out.UserPersistencePort;
import com.tsinsi.auth.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryMysql implements UserPersistencePort {

    private final UserJpaRepository userRepository;

    public UserRepositoryMysql(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userRepository.save(UserMapper.toUserEntity(user));
        user.setId(entity.getId());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toUser);
    }
}
