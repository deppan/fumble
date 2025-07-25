package com.tsinsi.auth.infrastructure;

import com.nimbusds.jwt.JWTClaimNames;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Map;

@Setter
@Getter
public class MapClaims {
    private Map<String, Object> map;

    public MapClaims(Map<String, Object> map) {
        this.map = map;
    }

    public boolean valid() {
        if (map == null || map.isEmpty()) {
            return false;
        }

        long timestamp = Instant.now().toEpochMilli() / 1000;
        long expiresAt = (long) map.get(JWTClaimNames.EXPIRATION_TIME);
        return timestamp < expiresAt;
    }

    public String subject() {
        return (String) map.get(JWTClaimNames.SUBJECT);
    }
}
