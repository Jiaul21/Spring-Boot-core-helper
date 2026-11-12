package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.exception.ApiException;
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
 * Global REST controller advice that intercepts {@link ApiException} instances,
 * standard bean validation failures, and unreadable JSON bodies,
 * transforming them into standardized {@link Response} bodies with appropriate HTTP status codes.
 * <p>
 * Configured with {@link Ordered#LOWEST_PRECEDENCE} so application-specific
 * exception handlers take priority when present.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class CoreExceptionHandler extends BaseExceptionHandler {

    /**
     * Handles any {@link ApiException} thrown by application code or controllers.
     *
     * @param ex the caught {@link ApiException}
     * @return a structured {@link ResponseEntity} containing a {@link Response}
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response> handleApiException(ApiException ex) {
        logException(ex, ex.getHttpStatus());
        return ApiResponseBuilder.build(ex.getHttpStatus(), ex.getMessage(), ex.getError(), ex.getData());
    }

    /**
     * Handles Spring standard {@code @Valid @RequestBody} bean validation failures.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    /**
     * Handles missing or malformed JSON payloads in HTTP request bodies.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Response> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        logException(ex, HttpStatus.BAD_REQUEST);
        return ApiResponseBuilder.build(HttpStatus.BAD_REQUEST, "Request body missing or malformed", ex.getMessage());
    }
}
