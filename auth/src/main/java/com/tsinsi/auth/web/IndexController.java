package com.tsinsi.auth.web;

import com.tsinsi.auth.application.in.SignInRequest;
import com.tsinsi.auth.application.in.SignUpRequest;
import com.tsinsi.auth.application.in.UserUseCase;
import com.tsinsi.auth.configuration.JwtHelper;
import com.tsinsi.auth.configuration.util.ClaimSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j(topic = "auth")
@RestController
public class IndexController {

    private final JwtHelper jwtHelper;
    private final UserUseCase userUseCase;
    private final AuthenticationManager authenticationManager;

    public IndexController(JwtHelper jwtHelper, UserUseCase userUseCase, AuthenticationManager authenticationManager) {
        this.jwtHelper = jwtHelper;
        this.userUseCase = userUseCase;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated SignUpRequest signupRequest) throws Exception {
        return userUseCase.signup(signupRequest);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Validated SignInRequest signInRequest) throws Exception {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        return ResponseEntity.ok(Map.of("token", sign(authenticate.getName())));
    }

    private String sign(String uid) throws Exception {
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(uid);
        claimSet.setUid(Long.parseLong(uid));
        return jwtHelper.onSign(claimSet);
    }
}
