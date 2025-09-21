package com.tsinsi.console.application.in;

import com.tsinsi.console.domin.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    User createUser(String name, String email);
}
