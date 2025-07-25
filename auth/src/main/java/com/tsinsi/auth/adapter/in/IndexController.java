package com.tsinsi.auth.adapter.in;

import com.tsinsi.auth.application.in.UserUseCase;
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
public class IndexController {

    private final JwtHelper jwtHelper;
    private final UserUseCase userUseCase;

    public IndexController(JwtHelper jwtHelper, UserUseCase userUseCase) {
        this.jwtHelper = jwtHelper;
        this.userUseCase = userUseCase;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated SignUpRequest signupRequest) throws Exception {
        UserResponse response = userUseCase.signUp(signupRequest);
        return ResponseEntity.ok(Map.of("token", sign(response)));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Validated SignInRequest signInRequest) throws Exception {
        UserResponse response = userUseCase.signIn(signInRequest);
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
