package com.tsinsi.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsinsi.account.configuration.IdJsonSerializer;
import lombok.Getter;

@Getter
public class Entity {
    @JsonSerialize(using = IdJsonSerializer.class)
    private long id;
}
