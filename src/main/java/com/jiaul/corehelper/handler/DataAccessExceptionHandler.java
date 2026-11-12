package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global REST controller advice for Spring Data Access / database constraint exceptions.
 * <p>
 * Conditionally registered via Auto-Configuration only when
 * {@code org.springframework.dao.DataIntegrityViolationException} is available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class DataAccessExceptionHandler {

    /**
     * Handles database integrity constraint violations (such as unique key or foreign key conflicts).
     *
     * @param ex the caught {@link DataIntegrityViolationException}
     * @return a structured 409 Conflict {@link ResponseEntity} containing a {@link Response}
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        ExceptionLogger.logException(ex, HttpStatus.CONFLICT);
        return ApiResponseBuilder.build(HttpStatus.CONFLICT, "Database constraint violation", ex.getMessage());
    }
}

