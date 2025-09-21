package com.tsinsi.console.application.out;

public interface CacheRepository {
    void set(String key, String value, long ttlSeconds);

    String get(String key);

    void delete(String key);
}
