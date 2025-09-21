package com.tsinsi.console.application.in;

import com.tsinsi.console.adapter.in.web.controller.auth.SignInRequest;
import com.tsinsi.console.adapter.in.web.controller.auth.SignUpRequest;
import com.tsinsi.console.application.response.AuthResponse;

public interface AuthService {
    AuthResponse signUp(SignUpRequest signUpRequest) throws Exception;

    AuthResponse signIn(SignInRequest signInRequest) throws Exception;

    AuthResponse signOut();
}
