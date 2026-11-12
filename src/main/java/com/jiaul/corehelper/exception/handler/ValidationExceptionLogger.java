package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global REST controller advice for Jakarta Bean Validation ({@code @Validated}) parameter violations.
 * <p>
 * Conditionally registered via Auto-Configuration only when {@code jakarta.validation.ConstraintViolationException}
 * is available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class ValidationExceptionLogger extends ExceptionLogger {

    /**
     * Handles Jakarta standard {@code @Validated} constraint violations on query/path parameters.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Response> handleConstraintViolations(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations()
                .forEach(error -> errors.put(error.getPropertyPath().toString(), error.getMessage()));

        logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Constraint violation", errors);
    }
}

