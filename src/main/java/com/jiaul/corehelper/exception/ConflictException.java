package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating a conflict with the current state of the target resource
 * (e.g., duplicate unique constraint violation, concurrent edit collision).
 * <p>
 * Corresponds to HTTP 409 (Conflict).
 */
public class ConflictException extends ApiException {

    /**
     * Constructs a new {@code ConflictException} with a message.
     *
     * @param message the detail message
     */
    public ConflictException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ConflictException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public ConflictException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code ConflictException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    conflict data or details
     */
    public ConflictException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code ConflictException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ConflictException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public ConflictException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code ConflictException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    conflict data or details
     * @param cause   underlying cause
     */
    public ConflictException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
