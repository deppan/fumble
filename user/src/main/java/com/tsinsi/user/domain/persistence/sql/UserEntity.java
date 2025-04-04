package com.tsinsi.user.domain.persistence.sql;

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
    private String username;
    @Column
    private String nickname;
    @Column
    private int gender;

    public UserEntity() {
    }

    public UserEntity(long id, String username, String nickname, int gender) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
    }

}
