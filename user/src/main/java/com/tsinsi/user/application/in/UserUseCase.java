package com.tsinsi.user.application.in;

import com.tsinsi.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserUseCase {

    Optional<List<User>> findUsers(long beforeId, long afterId);

    Optional<User> findOne(String username);

}
