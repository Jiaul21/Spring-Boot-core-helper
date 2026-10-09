package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the server understands the content type and syntax
 * of the request entity, but was unable to process the contained instructions
 * due to semantic errors (e.g., domain-level business rule violations).
 * <p>
 * Corresponds to HTTP 422 (Unprocessable Entity).
 */
public class UnprocessableEntityException extends ApiException {

    /**
     * Constructs a new {@code UnprocessableEntityException} with a message.
     *
     * @param message the detail message
     */
    public UnprocessableEntityException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UnprocessableEntityException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   semantic error details or error payload
     */
    public UnprocessableEntityException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code UnprocessableEntityException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   semantic error details or error payload
     * @param data    validation errors or domain details
     */
    public UnprocessableEntityException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code UnprocessableEntityException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public UnprocessableEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code UnprocessableEntityException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   semantic error details or error payload
     * @param cause   underlying cause
     */
    public UnprocessableEntityException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code UnprocessableEntityException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   semantic error details or error payload
     * @param data    validation errors or domain details
     * @param cause   underlying cause
     */
    public UnprocessableEntityException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
