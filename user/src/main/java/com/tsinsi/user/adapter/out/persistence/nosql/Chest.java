package com.tsinsi.user.adapter.out.persistence.nosql;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Getter
@Setter
@RedisHash(value = "chest", timeToLive = 60)
public class Chest implements Serializable {

    @Id
    private long id;
    @Indexed
    private String name;

}
