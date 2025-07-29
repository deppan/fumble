package com.tsinsi.foundation.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.sqids.Sqids;

import java.io.IOException;
import java.util.List;

@JsonComponent
public class IdJsonSerializer extends JsonSerializer<Long> {

    private Sqids sqids;

    public IdJsonSerializer() {
    }

    @Autowired
    public void setSqids(Sqids sqids) {
        this.sqids = sqids;
    }

    @Override
    public void serialize(Long value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        generator.writeString(sqids.encode(List.of(value)));
    }
}
