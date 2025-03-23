package com.tsinsi.auth.application.port.in;

import com.tsinsi.auth.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<User> signup(User user) throws Exception;

    User findOne(String username);

}
