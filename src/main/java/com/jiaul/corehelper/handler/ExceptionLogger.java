package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

/**
 * Utility class providing uniform diagnostic logging across all exception handlers.
 */
public final class ExceptionLogger {

    private static final Logger log = LoggerFactory.getLogger(ExceptionLogger.class);

    private ExceptionLogger() {
        // Prevent instantiation
    }

    /**
     * Logs detailed context about the exception including origin source class,
     * method, line, exception name, HTTP status code, message, error details, and payload data.
     *
     * @param ex     the exception to log
     * @param status the HTTP status code associated with the error response
     */
    public static void logException(Throwable ex, HttpStatus status) {
        if (ex == null) {
            return;
        }

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
                status, ex.getMessage(),
                ex instanceof ApiException apiEx ? (apiEx.getError() == null ? "N/A" : apiEx.getError()) : "N/A",
                ex instanceof ApiException apiEx ? (apiEx.getData() == null ? "N/A" : apiEx.getData()) : "N/A");
    }
}

