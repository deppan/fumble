package com.tsinsi.user.application;

import com.tsinsi.user.application.in.UserService;
import com.tsinsi.user.application.mapper.UserResponseMapper;
import com.tsinsi.user.application.out.UserRepository;
import com.tsinsi.user.application.response.UserResponse;
import com.tsinsi.user.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> findUsers(long beforeId, long afterId) {
        Optional<List<User>> users;
        if (beforeId > 0) {
            users = userRepository.findBefore(beforeId);
        } else {
            users = userRepository.findAfter(afterId);
        }
        return users.map(list -> list.stream()
                .map(UserResponseMapper::toUserResponse)
                .toList()
        ).orElse(List.of());
    }

    @Override
    public UserResponse findOne(String username) {
        return userRepository.findByUsername(username).map(UserResponseMapper::toUserResponse).orElse(null);
    }

}
