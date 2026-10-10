package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the server timed out waiting for the request from the client.
 * <p>
 * Corresponds to HTTP 408 (Request Timeout).
 */
public class RequestTimeoutException extends ApiException {

    /**
     * Constructs a new {@code RequestTimeoutException} with a message.
     *
     * @param message the detail message
     */
    public RequestTimeoutException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code RequestTimeoutException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public RequestTimeoutException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code RequestTimeoutException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured payload data or metadata
     */
    public RequestTimeoutException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code RequestTimeoutException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public RequestTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code RequestTimeoutException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public RequestTimeoutException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code RequestTimeoutException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured payload data or metadata
     * @param cause   underlying cause
     */
    public RequestTimeoutException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.REQUEST_TIMEOUT;
    }
}
