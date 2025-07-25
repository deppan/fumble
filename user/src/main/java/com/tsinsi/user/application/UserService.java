package com.tsinsi.user.application;

import com.tsinsi.user.application.in.UserUseCase;
import com.tsinsi.user.application.mapper.UserResponseMapper;
import com.tsinsi.user.application.out.UserPersistencePort;
import com.tsinsi.user.application.response.UserResponse;
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
    public List<UserResponse> findUsers(long beforeId, long afterId) {
        Optional<List<User>> users;
        if (beforeId > 0) {
            users = userPersistencePort.findBefore(beforeId);
        } else {
            users = userPersistencePort.findAfter(afterId);
        }
        return users.map(list -> list.stream()
                .map(UserResponseMapper::toUserResponse)
                .toList()
        ).orElse(List.of());
    }

    @Override
    public UserResponse findOne(String username) {
        return userPersistencePort.findByUsername(username).map(UserResponseMapper::toUserResponse).orElse(null);
    }

}
