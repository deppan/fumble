package com.tsinsi.configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class IdDeserializer extends JsonDeserializer<Long> {

    @Value("${hashids.salt}")
    private String salt = "";

    private final Hashids hashids = new Hashids(salt);

    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String text = p.getText();
        try {
            return hashids.decode(text)[0];
        } catch (Exception ignored) {
        }
        return 0L;
    }
}
