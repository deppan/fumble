package com.tsinsi.foundation.shared;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppException extends RuntimeException {

    private final HttpStatus status;
    private final String messageKey;

    public AppException(HttpStatus status) {
        this(status, null);
    }

    public AppException(HttpStatus status, String messageKey) {
        super();
        this.status = status;
        this.messageKey = messageKey;
    }
}
