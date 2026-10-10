package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception indicating that the server refuses to accept the request because the payload format is in an unsupported format.
 * <p>
 * Corresponds to HTTP 415 (Unsupported Media Type).
 */
public class UnsupportedMediaTypeException extends ApiException {

    /**
     * Constructs a new {@code UnsupportedMediaTypeException} with a message.
     *
     * @param message the detail message
     */
    public UnsupportedMediaTypeException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code UnsupportedMediaTypeException} with a message and error payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     */
    public UnsupportedMediaTypeException(String message, Object error) {
        super(message, error);
    }

    /**
     * Constructs a new {@code UnsupportedMediaTypeException} with a message, error payload, and data payload.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured payload data or metadata
     */
    public UnsupportedMediaTypeException(String message, Object error, Object data) {
        super(message, error, data);
    }

    /**
     * Constructs a new {@code UnsupportedMediaTypeException} with a message and cause.
     *
     * @param message the detail message
     * @param cause   underlying cause
     */
    public UnsupportedMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code UnsupportedMediaTypeException} with a message, error payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param cause   underlying cause
     */
    public UnsupportedMediaTypeException(String message, Object error, Throwable cause) {
        super(message, error, cause);
    }

    /**
     * Constructs a new {@code UnsupportedMediaTypeException} with a message, error payload, data payload, and cause.
     *
     * @param message the detail message
     * @param error   structured error details or error payload
     * @param data    structured payload data or metadata
     * @param cause   underlying cause
     */
    public UnsupportedMediaTypeException(String message, Object error, Object data, Throwable cause) {
        super(message, error, data, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNSUPPORTED_MEDIA_TYPE;
    }
}
