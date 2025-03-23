package com.tsinsi.auth.application.service;

import com.tsinsi.auth.application.port.in.UserService;
import com.tsinsi.auth.application.port.out.repository.UserRepository;
import com.tsinsi.auth.configuration.JwtHelper;
import com.tsinsi.auth.configuration.util.ClaimSet;
import com.tsinsi.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtHelper jwtHelper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtHelper jwtHelper) {
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public ResponseEntity<User> signup(User user) throws Exception {
        User tmp = userRepository.findByUsername(user.getUsername());
        if (tmp != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
        user = userRepository.save(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + sign(user.getId()));
        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }

    @Override
    public User findOne(String username) {
        return userRepository.findByUsername(username);
    }

    private String sign(Long uid) throws Exception {
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(String.valueOf(uid));
        claimSet.setUid(uid);
        return jwtHelper.onSign(claimSet);
    }

}
