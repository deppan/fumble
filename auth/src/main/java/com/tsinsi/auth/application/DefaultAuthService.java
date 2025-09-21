package com.tsinsi.auth.application;

import com.tsinsi.auth.adapter.in.web.controller.SignInRequest;
import com.tsinsi.auth.adapter.in.web.controller.SignUpRequest;
import com.tsinsi.auth.application.in.AuthService;
import com.tsinsi.auth.application.mapper.UserResponseMapper;
import com.tsinsi.auth.application.out.UserRepository;
import com.tsinsi.auth.application.response.UserResponse;
import com.tsinsi.auth.adapter.in.security.CustomUserDetails;
import com.tsinsi.auth.domain.User;
import com.tsinsi.foundation.shared.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public DefaultAuthService(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) {
        Optional<User> optional = userRepository.findByUsername(signUpRequest.getUsername());
        if (optional.isEmpty()) {
            throw new AppException(HttpStatus.CONFLICT);
        }
        User user = userRepository.save(signUpRequest.toUser());
        return UserResponseMapper.toUserResponse(user);
    }

    @Override
    public UserResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return new UserResponse(customUserDetails.getUid(), customUserDetails.getUsername(), customUserDetails.getNickname(), customUserDetails.getGender());
    }
}
