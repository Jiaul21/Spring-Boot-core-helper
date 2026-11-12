package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global REST controller advice for standard Spring Web ({@code spring-web}) request validation
 * and payload parsing exceptions.
 * <p>
 * Conditionally registered via Auto-Configuration only when {@link MethodArgumentNotValidException}
 * and {@link HttpMessageNotReadableException} are available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class SpringWebExceptionHandler {

    /**
     * Handles Spring standard {@code @Valid @RequestBody} bean validation failures.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ExceptionLogger.logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    /**
     * Handles missing or malformed JSON payloads in HTTP request bodies.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ExceptionLogger.logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Request body missing or malformed", ex.getMessage());
    }
}

