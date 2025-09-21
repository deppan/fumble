package com.tsinsi.console.infrastructure;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "whitelist")
@Setter
public class Whitelist {

    private String[] paths = null;

    public String[] getPaths() {
        return Objects.requireNonNullElseGet(paths, () -> new String[0]);
    }
}
