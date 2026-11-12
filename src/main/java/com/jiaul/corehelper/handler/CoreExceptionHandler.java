package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.exception.ApiException;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global REST controller advice that intercepts {@link ApiException} instances,
 * transforming them into standardized {@link Response} bodies with appropriate HTTP status codes.
 * <p>
 * Configured with {@link Ordered#LOWEST_PRECEDENCE} so application-specific
 * exception handlers take priority when present.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class CoreExceptionHandler {

    /**
     * Handles any {@link ApiException} thrown by application code or controllers.
     *
     * @param ex the caught {@link ApiException}
     * @return a structured {@link ResponseEntity} containing a {@link Response}
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response> handleApiException(ApiException ex) {
        ExceptionLogger.logException(ex, ex.getHttpStatus());
        return ApiResponseBuilder.build(ex.getHttpStatus(), ex.getMessage(), ex.getError(), ex.getData());
    }
}

