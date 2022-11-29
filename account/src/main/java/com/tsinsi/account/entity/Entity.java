package com.tsinsi.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tsinsi.configuration.IdDeserializer;
import com.tsinsi.configuration.IdJsonSerializer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity {
    @JsonSerialize(using = IdJsonSerializer.class)
    @JsonDeserialize(using = IdDeserializer.class)
    private long id;
}
