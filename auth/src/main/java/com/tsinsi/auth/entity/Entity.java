package com.tsinsi.auth.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsinsi.configuration.IdJsonSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity {
    @JsonSerialize(using = IdJsonSerializer.class)
    private long id;
}
