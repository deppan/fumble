package com.tsinsi.auth.adapter.out.persistence.repository;

import com.tsinsi.auth.adapter.out.persistence.entity.UserEntity;
import com.tsinsi.auth.adapter.out.persistence.mapper.UserMapper;
import com.tsinsi.auth.application.out.UserRepository;
import com.tsinsi.auth.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserPersistence implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserPersistence(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userJpaRepository.save(UserMapper.toUserEntity(user));
        user.setId(entity.getId());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(UserMapper::toUser);
    }
}
