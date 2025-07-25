package com.tsinsi.auth.application.in;

import com.tsinsi.auth.adapter.in.SignInRequest;
import com.tsinsi.auth.adapter.in.SignUpRequest;
import com.tsinsi.auth.application.response.UserResponse;

public interface UserUseCase {

    UserResponse signUp(SignUpRequest signUpRequest) throws Exception;

    UserResponse signIn(SignInRequest signInRequest) throws Exception;

}
