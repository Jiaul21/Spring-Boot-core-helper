package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the server understood the request but refuses to authorize it.
 * <p>
 * Corresponds to HTTP 403 (Forbidden).
 */
public class ForbiddenException extends ApiException {

    /**
     * Constructs a new {@code ForbiddenException} with a message.
     *
     * @param message the detail message
     */
    public ForbiddenException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ForbiddenException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public ForbiddenException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code ForbiddenException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured permission details or payload data
     */
    public ForbiddenException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code ForbiddenException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ForbiddenException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public ForbiddenException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code ForbiddenException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured permission details or payload data
     * @param cause   underlying cause
     */
    public ForbiddenException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
