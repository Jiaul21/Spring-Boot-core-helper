package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the server cannot process the request due to a client error
 * (e.g., malformed syntax, invalid parameters, or domain validation failure).
 * <p>
 * Corresponds to HTTP 400 (Bad Request).
 */
public class BadRequestException extends ApiException {

    /**
     * Constructs a new {@code BadRequestException} with a message.
     *
     * @param message the detail message
     */
    public BadRequestException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code BadRequestException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public BadRequestException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code BadRequestException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured validation or payload data
     */
    public BadRequestException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code BadRequestException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code BadRequestException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public BadRequestException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code BadRequestException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured validation or payload data
     * @param cause   underlying cause
     */
    public BadRequestException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
