package com.tsinsi.auth.application.in;

import com.tsinsi.auth.adapter.in.web.controller.SignInRequest;
import com.tsinsi.auth.adapter.in.web.controller.SignUpRequest;
import com.tsinsi.auth.application.response.UserResponse;

public interface AuthService {

    UserResponse signUp(SignUpRequest signUpRequest) throws Exception;

    UserResponse signIn(SignInRequest signInRequest) throws Exception;

}
