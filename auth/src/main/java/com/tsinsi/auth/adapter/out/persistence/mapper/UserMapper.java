package com.tsinsi.auth.adapter.out.persistence.mapper;

import com.tsinsi.auth.adapter.out.persistence.entity.UserEntity;
import com.tsinsi.auth.domain.model.User;

public class UserMapper {
    public static User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setNickname(userEntity.getNickname());
        user.setGender(userEntity.getGender());
        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setNickname(user.getNickname());
        entity.setGender(user.getGender());
        return entity;
    }
}
