package com.tsinsi.console.application;

import com.tsinsi.console.application.in.JwtService;
import com.tsinsi.console.application.out.CacheRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultJwtService implements JwtService {
    private final String prefix = "jti:";

    private final CacheRepository cacheRepository;

    public DefaultJwtService(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    public void saveJti(String uid, String jti, long ttlSeconds) {
        cacheRepository.set(prefix + uid, jti, ttlSeconds);
    }

    public boolean isValidJti(String uid, String jti) {
        String validJti = cacheRepository.get(prefix + uid);
        return jti.equals(validJti);
    }

    public void deleteJti(String uid) {
        cacheRepository.delete(prefix + uid);
    }
}
