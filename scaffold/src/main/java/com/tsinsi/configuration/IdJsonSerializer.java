package com.tsinsi.configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class IdJsonSerializer extends JsonSerializer<Long> {

    @Autowired
    private Hashids hashids;

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(hashids.encode(value));
    }
}
