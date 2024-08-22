package com.tsinsi.user.application.service;

import com.tsinsi.user.application.port.in.UserService;
import com.tsinsi.user.application.port.out.UserRepository;
import com.tsinsi.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAfterAccounts(long afterId) {
        return repository.findAfter(afterId);
    }

    @Override
    public List<User> findBeforeAccounts(long afterId) {
        return repository.findBefore(afterId);
    }

    @Override
    public User findOne(String username) {
        return repository.findByUsername(username);
    }
}
