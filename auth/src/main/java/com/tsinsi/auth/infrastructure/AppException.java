package com.tsinsi.auth.infrastructure;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(HttpStatus status) {
        super();
        this.status = status;
    }
}
