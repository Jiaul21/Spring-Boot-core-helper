package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * Global REST controller advice for Spring Web 6.1+ / Spring Boot 3.2+
 * controller method parameter validation failures ({@link HandlerMethodValidationException}).
 * <p>
 * Conditionally registered via Auto-Configuration only when
 * {@code org.springframework.web.method.annotation.HandlerMethodValidationException} is available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class SpringWebValidationExceptionHandler {

    /**
     * Handles Spring 6.1+ controller method parameter validation failures.
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Response> handleHandlerMethodValidation(HandlerMethodValidationException ex) {
        ExceptionLogger.logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Request parameter validation failed", ex.getMessage());
    }
}

