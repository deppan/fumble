package com.tsinsi.auth.application;

import com.tsinsi.auth.adapter.in.SignInRequest;
import com.tsinsi.auth.adapter.in.SignUpRequest;
import com.tsinsi.auth.application.in.UserUseCase;
import com.tsinsi.auth.application.mapper.UserResponseMapper;
import com.tsinsi.auth.application.out.UserPersistencePort;
import com.tsinsi.auth.application.response.UserResponse;
import com.tsinsi.auth.domain.model.User;
import com.tsinsi.auth.infrastructure.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) {
        Optional<User> optional = userPersistencePort.findByUsername(signUpRequest.getUsername());
        if (optional.isEmpty()) {
            throw new AppException(HttpStatus.CONFLICT);
        }
        User user = userPersistencePort.save(signUpRequest.toUser());
        return UserResponseMapper.toUserResponse(user);
    }

    @Override
    public UserResponse signIn(SignInRequest signInRequest) {
        return userPersistencePort.findByUsername(signInRequest.getUsername()).map(user -> {
            if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
                throw new AppException(HttpStatus.UNAUTHORIZED);
            }
            return UserResponseMapper.toUserResponse(user);
        }).orElseThrow();
    }
}
