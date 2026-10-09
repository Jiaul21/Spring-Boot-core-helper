package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the client has sent too many requests in a given amount of time
 * (e.g., rate limiting, quota exhaustion).
 * <p>
 * Corresponds to HTTP 429 (Too Many Requests).
 */
public class TooManyRequestsException extends ApiException {

    /**
     * Constructs a new {@code TooManyRequestsException} with a message.
     *
     * @param message the detail message
     */
    public TooManyRequestsException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code TooManyRequestsException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public TooManyRequestsException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code TooManyRequestsException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured quota or retry details
     */
    public TooManyRequestsException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code TooManyRequestsException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public TooManyRequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code TooManyRequestsException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public TooManyRequestsException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code TooManyRequestsException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured quota or retry details
     * @param cause   underlying cause
     */
    public TooManyRequestsException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.TOO_MANY_REQUESTS;
    }
}
