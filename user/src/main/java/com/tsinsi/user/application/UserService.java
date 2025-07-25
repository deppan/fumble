package com.tsinsi.user.application;

import com.tsinsi.user.application.in.UserUseCase;
import com.tsinsi.user.application.out.UserPersistencePort;
import com.tsinsi.user.domain.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserUseCase {

    private final UserPersistencePort userPersistencePort;

    public UserService(UserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Optional<List<User>> findUsers(long beforeId, long afterId) {
        Optional<List<User>> users;
        if (beforeId > 0) {
            users = userPersistencePort.findBefore(beforeId);
        } else {
            users = userPersistencePort.findAfter(afterId);
        }
        return users;
    }

    @Override
    public Optional<User> findOne(String username) {
        return userPersistencePort.findByUsername(username);
    }

}
