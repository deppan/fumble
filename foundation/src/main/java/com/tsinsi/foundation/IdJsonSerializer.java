package com.tsinsi.foundation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.sqids.Sqids;

import java.io.IOException;
import java.util.List;

public class IdJsonSerializer extends JsonSerializer<Long> {

    private final Sqids sqids;

    public IdJsonSerializer(Sqids sqids) {
        this.sqids = sqids;
    }

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(sqids.encode(List.of(value)));
    }
}
