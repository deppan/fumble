package com.tsinsi.console.application.in;

public interface JwtService {
    void saveJti(String uid, String jti, long ttlSeconds);

    boolean isValidJti(String uid, String jti);

    void deleteJti(String uid);
}
