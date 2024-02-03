package com.tsinsi.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.sqids.Sqids;

import java.io.IOException;

public class IdDeserializer extends JsonDeserializer<Long> {

    private final Sqids sqids;

    public IdDeserializer(Sqids sqids) {
        this.sqids = sqids;
    }

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        try {
            return sqids.decode(text).get(0);
        } catch (Exception ignored) {
        }
        return 0L;
    }
}
