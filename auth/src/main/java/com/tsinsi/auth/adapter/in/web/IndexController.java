package com.tsinsi.auth.adapter.in.web;

import com.tsinsi.auth.adapter.in.web.param.SignIn;
import com.tsinsi.auth.adapter.in.web.param.SignUp;
import com.tsinsi.auth.application.port.in.UserService;
import com.tsinsi.auth.configuration.JwtHelper;
import com.tsinsi.auth.configuration.util.ClaimSet;
import com.tsinsi.auth.entity.User;
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
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public IndexController(JwtHelper jwtHelper, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtHelper = jwtHelper;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated SignUp signup) throws Exception {
        User tmp = signup.toUser();
        return userService.signup(tmp);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@Validated SignIn signIn) throws Exception {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword());
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
