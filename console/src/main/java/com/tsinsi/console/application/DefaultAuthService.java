package com.tsinsi.console.application;

import com.tsinsi.console.adapter.in.security.CustomUserDetails;
import com.tsinsi.console.adapter.in.web.controller.auth.SignInRequest;
import com.tsinsi.console.adapter.in.web.controller.auth.SignUpRequest;
import com.tsinsi.console.application.in.AuthService;
import com.tsinsi.console.application.in.JwtService;
import com.tsinsi.console.application.out.UserRepository;
import com.tsinsi.console.application.response.AuthResponse;
import com.tsinsi.console.domin.User;
import com.tsinsi.console.infrastructure.JwtProvider;
import com.tsinsi.foundation.shared.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultAuthService implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final JwtService jwtService;

    public DefaultAuthService(AuthenticationManager authenticationManager, UserRepository userRepository, JwtProvider jwtProvider, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) throws Exception {
        Optional<User> optional = userRepository.findByUsername(signUpRequest.getUsername());
        if (optional.isEmpty()) {
            throw new AppException(HttpStatus.CONFLICT);
        }
        User user = userRepository.save(signUpRequest.toUser());
        String jti = UUID.randomUUID().toString();
        jwtService.saveJti("" + user.getUid(), jti, 7 * 24 * 3600);
        String token = jwtProvider.generate(user.getUid(), user.getUsername(), jti);
        return new AuthResponse(user.getUsername(), user.getNickname(), user.getGender(), token);
    }

    @Override
    public AuthResponse signIn(SignInRequest signInRequest) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String jti = UUID.randomUUID().toString();
        jwtService.saveJti("" + customUserDetails.getUid(), jti, 7 * 24 * 3600);
        String token = jwtProvider.generate(customUserDetails.getUid(), customUserDetails.getUsername(), jti);
        return new AuthResponse(customUserDetails.getUsername(), customUserDetails.getNickname(), customUserDetails.getGender(), token);
    }

    @Override
    public AuthResponse signOut() {
        return null;
    }
}
