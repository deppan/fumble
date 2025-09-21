package com.tsinsi.auth.adapter.in.web.controller;

import com.tsinsi.auth.application.in.AuthService;
import com.tsinsi.auth.application.response.UserResponse;
import com.tsinsi.auth.infrastructure.ClaimSet;
import com.tsinsi.auth.infrastructure.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j(topic = "auth")
@RestController
public class AuthController {

    private final JwtHelper jwtHelper;
    private final AuthService authService;

    public AuthController(JwtHelper jwtHelper, AuthService authService) {
        this.jwtHelper = jwtHelper;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated SignUpRequest signupRequest) throws Exception {
        UserResponse response = authService.signUp(signupRequest);
        return ResponseEntity.ok(Map.of("token", sign(response)));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Validated SignInRequest signInRequest) throws Exception {
        UserResponse response = authService.signIn(signInRequest);
        return ResponseEntity.ok(Map.of("token", sign(response)));
    }

    private String sign(UserResponse response) throws Exception {
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(response.getUsername());
        claimSet.setUid(response.getId());
        return jwtHelper.onSign(claimSet);
    }
}
