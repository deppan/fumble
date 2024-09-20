package com.tsinsi.auth.configuration.util;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private HttpStatus status;

    public AppException(HttpStatus status) {
        super();
        this.status = status;
    }
}
