package com.tsinsi.auth.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsinsi.foundation.IdJsonSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Entity {
    @JsonSerialize(using = IdJsonSerializer.class)
    private long id;
}
