package com.tsinsi.gateway.configuration;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Configuration
@ConfigurationProperties(prefix = "whitelist")
public class Whitelist {

    private List<String> path = null;

    public List<String> getPath() {
        return path != null ? path : List.of();
    }
}
