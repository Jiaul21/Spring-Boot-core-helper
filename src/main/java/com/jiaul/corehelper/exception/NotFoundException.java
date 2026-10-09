package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the requested resource could not be found.
 * <p>
 * Corresponds to HTTP 404 (Not Found).
 */
public class NotFoundException extends ApiException {

    /**
     * Constructs a new {@code NotFoundException} with a message.
     *
     * @param message the detail message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code NotFoundException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public NotFoundException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code NotFoundException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured details or payload data
     */
    public NotFoundException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code NotFoundException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code NotFoundException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public NotFoundException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code NotFoundException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured details or payload data
     * @param cause   underlying cause
     */
    public NotFoundException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
