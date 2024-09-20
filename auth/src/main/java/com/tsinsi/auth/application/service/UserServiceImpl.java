package com.tsinsi.auth.application.service;

import com.tsinsi.auth.application.port.in.UserService;
import com.tsinsi.auth.application.port.out.UserRepository;
import com.tsinsi.auth.configuration.util.AppException;
import com.tsinsi.auth.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signup(User user) throws Exception {
        if (Strings.isEmpty(user.getUsername()) || Strings.isEmpty(user.getPassword())) {
            throw new AppException(HttpStatus.BAD_REQUEST);
        }
        User tmp = userRepository.findByUsername(user.getUsername());
        if (tmp != null) {
            throw new AppException(HttpStatus.CONFLICT);
        }
        return userRepository.signup(user);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }
}
