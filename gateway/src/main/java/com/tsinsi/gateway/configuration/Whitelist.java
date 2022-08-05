package com.tsinsi.gateway.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@ConfigurationProperties(prefix = "whitelist")
@Setter
class Whitelist {

    private List<String> paths = null;

    public List<String> getPaths() {
        return Objects.requireNonNullElse(paths, Collections.emptyList());
    }
}
