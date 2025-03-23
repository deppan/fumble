package com.tsinsi.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsinsi.foundation.IdDeserializer;
import com.tsinsi.foundation.IdJsonSerializer;
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
    @JsonDeserialize(using = IdDeserializer.class)
    @JsonSerialize(using = IdJsonSerializer.class)
    private long id;
    @Column
    @JsonIgnore
    private String password;
    @Column
    private String username;
    @Column
    private String nickname;
    @Column
    private int gender;

}
