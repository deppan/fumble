package com.tsinsi.user.adapter.out.persistence.mysql.repository;

import com.tsinsi.user.adapter.out.persistence.mysql.mapper.UserMapper;
import com.tsinsi.user.application.out.UserRepository;
import com.tsinsi.user.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserPersistence implements UserRepository {

    private final UserJpaRepository userRepository;

    public UserPersistence(UserJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Cacheable(value = "user", key = "#username", unless = "#result == null")
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username).map(UserMapper::toUser);
    }

    @Override
    public Optional<List<User>> findBefore(long id) {
        return userRepository.findBefore(id).map(entities -> entities.stream().map(UserMapper::toUser).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<User>> findAfter(long id) {
        return userRepository.findAfter(id).map(entities -> entities.stream().map(UserMapper::toUser).collect(Collectors.toList()));
    }

}
