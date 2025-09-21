package com.tsinsi.console.adapter.out.persistence.mysql;

import com.tsinsi.console.adapter.out.persistence.entity.UserEntity;
import com.tsinsi.console.adapter.out.persistence.mapper.UserMapper;
import com.tsinsi.console.application.out.UserRepository;
import com.tsinsi.console.domin.User;
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
        user.setUid(entity.getId());
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(UserMapper::toUser);
    }
}
