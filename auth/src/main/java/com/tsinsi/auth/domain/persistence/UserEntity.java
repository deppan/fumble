package com.tsinsi.auth.domain.persistence;

import com.tsinsi.auth.domain.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @Column
    private long id;
    @Column
    private String password;
    @Column
    private String username;
    @Column
    private String nickname;
    @Column
    private int gender;

    public User toDomain() {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setNickname(nickname);
        user.setGender(gender);
        return user;
    }

    public static UserEntity fromDomain(User user) {
        UserEntity entity = new UserEntity();
        entity.setPassword(user.getPassword());
        entity.setUsername(user.getUsername());
        entity.setNickname(user.getNickname());
        entity.setGender(user.getGender());
        return entity;
    }

}
