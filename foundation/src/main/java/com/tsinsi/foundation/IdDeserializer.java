package com.tsinsi.foundation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.sqids.Sqids;

import java.io.IOException;

@JsonComponent
public class IdDeserializer extends JsonDeserializer<Long> {

    private Sqids sqids;

    @Autowired
    public void setSqids(Sqids sqids) {
        this.sqids = sqids;
    }

    @Override
    public Long deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        String text = parser.getText();
        try {
            return sqids.decode(text).getFirst();
        } catch (Exception ignored) {
        }
        return 0L;
    }
}
