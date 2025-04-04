package com.tsinsi.foundation;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class FumbleExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public FumbleExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnknownException(Exception exception, HttpServletRequest request) {
        log.error(request.getRequestURI(), exception);

        Map<String, Object> map = Map.of("url", request.getRequestURI(), "date", new Date());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @Nonnull MethodArgumentNotValidException ex,
            @Nonnull HttpHeaders headers,
            @Nonnull HttpStatusCode status,
            @Nonnull WebRequest request) {
        return this.handleBindException(ex, request);
    }

    private ResponseEntity<Object> handleBindException(BindException exception, WebRequest request) {
        String url = ((ServletWebRequest) request).getRequest().getRequestURI();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
            Map<String, ?> map = Map.of("url", url, "message", message, "date", new Date());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("url", url, "date", new Date()));
    }
}
