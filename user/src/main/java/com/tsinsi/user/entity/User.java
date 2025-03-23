package com.tsinsi.user.entity;

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
public class User {
    @Id
    @Column
    private long id;
    @Column
    private String username;
    @Column
    private String nickname;
    @Column
    private int gender;

}
