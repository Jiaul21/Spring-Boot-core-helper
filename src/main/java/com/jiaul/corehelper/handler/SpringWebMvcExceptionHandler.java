package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * Global REST controller advice for Spring Web MVC 6.1+ / Spring Boot 3.2+
 * unmapped endpoint 404 errors ({@link NoResourceFoundException}).
 * <p>
 * Conditionally registered via Auto-Configuration only when
 * {@code org.springframework.web.servlet.resource.NoResourceFoundException} is available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class SpringWebMvcExceptionHandler {

    /**
     * Handles Spring 6.1+ 404 errors when an incoming HTTP request does not match any endpoint.
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Response> handleNoResourceFoundException(NoResourceFoundException ex) {
        ExceptionLogger.logException(ex, HttpStatus.NOT_FOUND);
        return ApiResponseBuilder.build(HttpStatus.NOT_FOUND, "URL does not match with any api", ex.getMessage());
    }
}

