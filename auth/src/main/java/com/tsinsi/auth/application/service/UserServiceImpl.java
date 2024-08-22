package com.tsinsi.auth.application.service;

import com.tsinsi.auth.application.port.in.UserService;
import com.tsinsi.auth.application.port.out.UserRepository;
import com.tsinsi.auth.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signup(User account) {
        if (Strings.isEmpty(account.getUsername()) || Strings.isEmpty(account.getPassword())) {
            throw new RuntimeException();
        }
        return userRepository.signup(account);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }
}
