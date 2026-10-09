package com.jiaul.corehelper.response;

import com.jiaul.corehelper.dto.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base abstract response builder providing core low-level {@link ResponseEntity} factory methods.
 */
public abstract class ResponseBuilder {

    protected ResponseBuilder() {
        // Prevent direct instantiation from outside the package hierarchy
    }

    /**
     * Builds a {@link ResponseEntity} containing a standardized {@link Response} with message, error, and data.
     *
     * @param status  the HTTP status
     * @param message the response message
     * @param error   the error details or error payload, or {@code null}
     * @param data    the payload data or structured metadata, or {@code null}
     * @return the configured {@link ResponseEntity}
     */
    public static ResponseEntity<Response> build(HttpStatus status, String message, Object error, Object data) {
        return ResponseEntity.status(status).body(new Response(message, error, data));
    }

    /**
     * Builds a {@link ResponseEntity} containing a standardized {@link Response} with message and error.
     *
     * @param status  the HTTP status
     * @param message the response message
     * @param error   the error details or error payload, or {@code null}
     * @return the configured {@link ResponseEntity}
     */
    public static ResponseEntity<Response> build(HttpStatus status, String message, Object error) {
        return ResponseEntity.status(status).body(new Response(message, error));
    }

    /**
     * Builds a {@link ResponseEntity} containing a standardized {@link Response} with message.
     *
     * @param status  the HTTP status
     * @param message the response message
     * @return the configured {@link ResponseEntity}
     */
    public static ResponseEntity<Response> build(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new Response(message));
    }

    /**
     * Builds a {@link ResponseEntity} containing the specified raw payload.
     *
     * @param <T>    the type of the response body
     * @param status the HTTP status
     * @param data   the response payload
     * @return the configured {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> build(HttpStatus status, T data) {
        return ResponseEntity.status(status).body(data);
    }

    /**
     * Builds a {@link ResponseEntity} containing headers and a specified payload.
     *
     * @param <T>     the type of the response body
     * @param status  the HTTP status
     * @param headers response headers
     * @param data    the response payload
     * @return the configured {@link ResponseEntity}
     */
    public static <T> ResponseEntity<T> build(HttpStatus status, HttpHeaders headers, T data) {
        return ResponseEntity.status(status).headers(headers).body(data);
    }
}
