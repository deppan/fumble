package com.tsinsi.auth.application;

import com.tsinsi.auth.adapter.in.SignInRequest;
import com.tsinsi.auth.adapter.in.SignUpRequest;
import com.tsinsi.auth.application.in.UserUseCase;
import com.tsinsi.auth.application.mapper.UserResponseMapper;
import com.tsinsi.auth.application.out.UserPersistencePort;
import com.tsinsi.auth.application.response.UserResponse;
import com.tsinsi.auth.configuration.AuthUser;
import com.tsinsi.auth.domain.model.User;
import com.tsinsi.foundation.shared.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final AuthenticationManager authenticationManager;
    private final UserPersistencePort userPersistencePort;

    public UserService(AuthenticationManager authenticationManager, UserPersistencePort userPersistencePort) {
        this.authenticationManager = authenticationManager;
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
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return new UserResponse(authUser.getUid(), authUser.getUsername(), authUser.getNickname(), authUser.getGender());
    }
}
