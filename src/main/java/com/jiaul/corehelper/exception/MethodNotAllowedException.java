package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the request HTTP method is known by the server but is not supported by the target resource.
 * <p>
 * Corresponds to HTTP 405 (Method Not Allowed).
 */
public class MethodNotAllowedException extends ApiException {

    /**
     * Constructs a new {@code MethodNotAllowedException} with a message.
     *
     * @param message the detail message
     */
    public MethodNotAllowedException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code MethodNotAllowedException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public MethodNotAllowedException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code MethodNotAllowedException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured payload data or metadata
     */
    public MethodNotAllowedException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code MethodNotAllowedException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public MethodNotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code MethodNotAllowedException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public MethodNotAllowedException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code MethodNotAllowedException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured payload data or metadata
     * @param cause   underlying cause
     */
    public MethodNotAllowedException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.METHOD_NOT_ALLOWED;
    }
}
