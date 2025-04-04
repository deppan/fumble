package com.tsinsi.user.application;

import com.tsinsi.user.application.in.UserUseCase;
import com.tsinsi.user.application.out.UserPersistencePort;
import com.tsinsi.user.domain.persistence.sql.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public List<UserEntity> findAfterUsers(long afterId) {
        return userPersistencePort.findAfter(afterId);
    }

    @Override
    public List<UserEntity> findBeforeUsers(long afterId) {
        return userPersistencePort.findBefore(afterId);
    }

    @Override
    public UserEntity findOne(String username) {
        return userPersistencePort.findByUsername(username);
    }

}
