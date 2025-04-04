package com.tsinsi.auth.application.in;

import com.tsinsi.auth.domain.persistence.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserUseCase {

    ResponseEntity<?> signup(SignUpRequest signUpRequest) throws Exception;

    UserEntity findOne(String username);

}
