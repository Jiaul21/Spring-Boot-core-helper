package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global REST controller advice for Jakarta Persistence (JPA) entity lookup failures.
 * <p>
 * Conditionally registered via Auto-Configuration only when
 * {@code jakarta.persistence.EntityNotFoundException} is available on the application classpath.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class JpaExceptionHandler {

    /**
     * Handles JPA {@link EntityNotFoundException} failures thrown by {@code EntityManager} or JPA repositories.
     *
     * @param ex the caught {@link EntityNotFoundException}
     * @return a structured 404 Not Found {@link ResponseEntity} containing a {@link Response}
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleEntityNotFoundException(EntityNotFoundException ex) {
        ExceptionLogger.logException(ex, HttpStatus.NOT_FOUND);
        return ApiResponseBuilder.build(HttpStatus.NOT_FOUND, "Entity Persistence error", ex.getMessage());
    }
}

