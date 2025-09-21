package com.tsinsi.console.adapter.in.web.controller.auth;

import com.tsinsi.console.application.in.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    ResponseEntity<Object> signUp(@Validated SignUpRequest signUpRequest) throws Exception {
        return ResponseEntity.ok().body(authService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    ResponseEntity<Object> signIn(@Validated SignInRequest signInRequest) throws Exception {
        return ResponseEntity.ok().body(authService.signIn(signInRequest));
    }

    @DeleteMapping("/signout")
    ResponseEntity<Object> signOut(String username, String password) {
        return ResponseEntity.ok().body(null);
    }
}
