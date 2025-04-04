package com.tsinsi.user.application.in;

import com.tsinsi.user.domain.persistence.sql.UserEntity;

import java.util.List;

public interface UserUseCase {

    List<UserEntity> findAfterUsers(long afterId);

    List<UserEntity> findBeforeUsers(long beforeId);

    UserEntity findOne(String username);

}
