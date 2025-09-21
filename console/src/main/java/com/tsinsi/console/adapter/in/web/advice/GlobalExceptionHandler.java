package com.tsinsi.console.adapter.in.web.advice;

import com.tsinsi.foundation.configuration.FoundationExceptionHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends FoundationExceptionHandler {

    public GlobalExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException exception) {
        String messageKey = switch (exception) {
            case BadCredentialsException ignored -> "user.bad-credentials";
            case DisabledException ignored -> "user.disabled";
            case LockedException ignored -> "user.locked";
            default -> "authentication.error";
        };
        String localizedMessage = messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", localizedMessage, "date", new Date()));
    }
}
