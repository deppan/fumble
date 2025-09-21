package com.tsinsi.user.adapter.out.persistence.mysql.mapper;

import com.tsinsi.user.adapter.out.persistence.mysql.entity.UserEntity;
import com.tsinsi.user.domain.User;

public class UserMapper {
    public static User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setNickname(userEntity.getNickname());
        user.setGender(userEntity.getGender());
        return user;
    }
}
