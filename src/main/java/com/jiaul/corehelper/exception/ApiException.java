package com.jiaul.corehelper.exception;

import org.springframework.http.HttpStatus;

/**
 * Base abstract exception class for all API-level application exceptions.
 * <p>
 * Carries an optional error payload (such as field errors or error metadata),
 * an optional structured data payload, and defines the associated {@link HttpStatus}.
 */
public abstract class ApiException extends RuntimeException {

    private final Object error;
    private final Object data;

    /**
     * Constructs a new {@code ApiException} with the specified detail message.
     *
     * @param message the detail message sent to {@link RuntimeException}
     */
    protected ApiException(String message) {
        super(message);
        this.error = null;
        this.data = null;
    }

    /**
     * Constructs a new {@code ApiException} with detail message and error payload.
     *
     * @param message the detail message sent to {@link RuntimeException}
     * @param error   the structured error details or error payload
     */
    protected ApiException(String message, Object error) {
        super(message);
        this.error = error;
        this.data = null;
    }

    /**
     * Constructs a new {@code ApiException} with detail message, error payload, and data payload.
     *
     * @param message the detail message sent to {@link RuntimeException}
     * @param error   the structured error details or error payload
     * @param data    the structured payload data or metadata
     */
    protected ApiException(String message, Object error, Object data) {
        super(message);
        this.error = error;
        this.data = data;
    }

    /**
     * Constructs a new {@code ApiException} with detail message and cause.
     *
     * @param message the detail message sent to {@link RuntimeException}
     * @param cause   the cause of this exception
     */
    protected ApiException(String message, Throwable cause) {
        super(message, cause);
        this.error = null;
        this.data = null;
    }

    /**
     * Constructs a new {@code ApiException} with detail message, error payload, and cause.
     *
     * @param message the detail message sent to {@link RuntimeException}
     * @param error   the structured error details or error payload
     * @param cause   the cause of this exception
     */
    protected ApiException(String message, Object error, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.data = null;
    }

    /**
     * Constructs a new {@code ApiException} with detail message, error payload, data payload, and cause.
     *
     * @param message the detail message sent to {@link RuntimeException}
     * @param error   the structured error details or error payload
     * @param data    the structured payload data or metadata
     * @param cause   the cause of this exception
     */
    protected ApiException(String message, Object error, Object data, Throwable cause) {
        super(message, cause);
        this.error = error;
        this.data = data;
    }

    /**
     * Returns the structured error details associated with this exception.
     *
     * @return the error payload, or {@code null} if none was provided
     */
    public Object getError() {
        return error;
    }

    /**
     * Returns the payload data or metadata associated with this exception.
     *
     * @return the data payload, or {@code null} if none was provided
     */
    public Object getData() {
        return data;
    }

    /**
     * Returns the HTTP status associated with this exception.
     *
     * @return the corresponding {@link HttpStatus}
     */
    public abstract HttpStatus getHttpStatus();
}