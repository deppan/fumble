package com.tsinsi.auth.application.port.in;

import com.tsinsi.auth.entity.User;

public interface UserService {

    User signup(User account) throws Exception;

    User findOne(String username);

}
