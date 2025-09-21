package com.tsinsi.console.application;

import com.tsinsi.console.application.in.UserService;
import com.tsinsi.console.domin.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUseService implements UserService {

    @Override
    public List<User> getUsers() {
        return List.of();
    }

    @Override
    public User createUser(String name, String email) {
        return null;
    }
}
