package com.jiaul.corehelper.exception;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.response.ApiResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global REST controller advice that intercepts all {@link ApiException} instances
 * and transforms them into standardized {@link Response} bodies with appropriate HTTP status codes.
 * <p>
 * Configured with {@link Ordered#LOWEST_PRECEDENCE} so application-specific exception handlers
 * take priority when present.
 */
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class CoreExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(CoreExceptionHandler.class);

    /**
     * Handles any {@link ApiException} thrown by application code or controllers.
     * <p>
     * Logs the exception details and returns a standardized {@link ResponseEntity}
     * matching the status, message, error details, and payload data of the exception.
     *
     * @param ex the caught {@link ApiException}
     * @return a structured {@link ResponseEntity} containing a {@link Response}
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response> handleApiException(ApiException ex) {
        logException(ex);
        return ApiResponseBuilder.build(ex.getHttpStatus(), ex.getMessage(), ex.getError(), ex.getData());
    }

    /**
     * Logs detailed context about the exception including origin source class, method, line,
     * status, message, error details, and payload data.
     *
     * @param ex the exception to log
     */
    protected void logException(ApiException ex) {
        StackTraceElement origin = null;
        if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
            origin = ex.getStackTrace()[0];
        }

        String originClass = origin != null ? origin.getClassName() : "UnknownClass";
        String originMethod = origin != null ? origin.getMethodName() : "UnknownMethod";
        int line = origin != null ? origin.getLineNumber() : -1;

        log.error("""
                        
                        Origin     : {}.{}:{}
                        Exception  : {}
                        HttpStatus : {}
                        Message    : {}
                        Error      : {}
                        Data       : {}
                        """,
                originClass, originMethod, line,
                ex.getClass().getSimpleName(),
                ex.getHttpStatus(),
                ex.getMessage(),
                ex.getError() == null ? "N/A" : ex.getError(),
                ex.getData() == null ? "N/A" : ex.getData());
    }
}
