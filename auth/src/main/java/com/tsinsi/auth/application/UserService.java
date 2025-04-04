package com.tsinsi.auth.application;

import com.tsinsi.auth.application.in.SignUpRequest;
import com.tsinsi.auth.application.in.UserUseCase;
import com.tsinsi.auth.application.out.UserPersistencePort;
import com.tsinsi.auth.configuration.JwtHelper;
import com.tsinsi.auth.configuration.util.ClaimSet;
import com.tsinsi.auth.domain.persistence.UserEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;
    private final JwtHelper jwtHelper;

    public UserService(UserPersistencePort userPersistencePort, JwtHelper jwtHelper) {
        this.userPersistencePort = userPersistencePort;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public ResponseEntity<?> signup(SignUpRequest signUpRequest) throws Exception {
        UserEntity userEntity = userPersistencePort.findByUsername(signUpRequest.getUsername());
        if (userEntity != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of());
        }
        userEntity = userPersistencePort.save(signUpRequest.toEntity());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + sign(userEntity.getId()));
        return new ResponseEntity<>(signUpRequest, headers, HttpStatus.OK);
    }

    @Override
    public UserEntity findOne(String username) {
        return userPersistencePort.findByUsername(username);
    }

    private String sign(Long uid) throws Exception {
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(String.valueOf(uid));
        claimSet.setUid(uid);
        return jwtHelper.onSign(claimSet);
    }

}
