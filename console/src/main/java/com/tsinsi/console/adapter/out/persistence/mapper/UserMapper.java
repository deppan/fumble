package com.tsinsi.console.adapter.out.persistence.mapper;


import com.tsinsi.console.adapter.out.persistence.entity.UserEntity;
import com.tsinsi.console.domin.User;

public class UserMapper {
    public static User toUser(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        User user = new User();
        user.setUid(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setNickname(userEntity.getNickname());
        user.setGender(userEntity.getGender());
        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getUid());
        entity.setUsername(user.getUsername());
        entity.setPassword(user.getPassword());
        entity.setNickname(user.getNickname());
        entity.setGender(user.getGender());
        return entity;
    }
}
