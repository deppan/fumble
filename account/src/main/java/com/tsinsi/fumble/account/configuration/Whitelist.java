package com.tsinsi.fumble.account.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "whitelist")
@Setter
class Whitelist {

    private String[] paths = null;

    public String[] getPaths() {
        return Objects.requireNonNullElseGet(paths, () -> new String[0]);
    }
}
