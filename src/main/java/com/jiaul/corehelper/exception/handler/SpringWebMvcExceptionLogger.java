package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global REST controller advice for Spring 6.1+ / Spring Boot 3.2+ web MVC exceptions:
 * method parameter validation and unmapped endpoint 404 handling.
 * <p>
 * Conditionally registered via Auto-Configuration only when modern Spring Web MVC classes
 * are available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class SpringWebMvcExceptionLogger extends ExceptionLogger {

    /**
     * Handles Spring 6.1+ controller method parameter validation failures.
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Response> handleHandlerMethodValidation(HandlerMethodValidationException ex) {
        logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Request parameter validation failed", ex.getMessage());
    }

    /**
     * Handles Spring 6.1+ 404 errors when an incoming HTTP request does not match any endpoint.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Response> handleNoResourceFoundException(NoResourceFoundException ex) {
        logException(ex, HttpStatus.NOT_FOUND);
        return ApiResponseBuilder.build(HttpStatus.NOT_FOUND, "URL does not match with any api", ex.getMessage());
    }
}

