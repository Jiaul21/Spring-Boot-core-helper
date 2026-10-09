package com.jiaul.corehelper.response;

import com.jiaul.corehelper.dto.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * Abstract builder providing factory methods for standard HTTP 2xx (Success) responses.
 */
public abstract class SuccessResponseBuilder extends ResponseBuilder {

    protected SuccessResponseBuilder() {
        super();
    }

    // -------------------------------------------------------------
    // Success Responses (2xx)
    // -------------------------------------------------------------

    /**
     * Returns an HTTP 200 (OK) response with an empty body.
     *
     * @param <T> the response body type
     * @return a 200 OK {@link ResponseEntity} with no body
     */
    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    /**
     * Returns an HTTP 200 (OK) response with a message and payload data wrapped in {@link Response}.
     *
     * @param message success message
     * @param data    payload data
     * @return a 200 OK {@link ResponseEntity} with wrapped {@link Response}
     */
    public static ResponseEntity<Response> ok(String message, Object data) {
        return build(HttpStatus.OK, message, null, data);
    }

    /**
     * Returns an HTTP 200 (OK) response with a raw data payload.
     *
     * @param <T>  the payload type
     * @param data the response data
     * @return a 200 OK {@link ResponseEntity} with body
     */
    public static <T> ResponseEntity<T> ok(T data) {
        return build(HttpStatus.OK, data);
    }

    /**
     * Returns an HTTP 200 (OK) response with headers and data payload.
     *
     * @param <T>     the payload type
     * @param headers HTTP headers
     * @param data    the response data
     * @return a 200 OK {@link ResponseEntity} with headers and body
     */
    public static <T> ResponseEntity<T> ok(HttpHeaders headers, T data) {
        return build(HttpStatus.OK, headers, data);
    }

    /**
     * Returns an HTTP 201 (Created) response with an empty body.
     *
     * @param <T> the response body type
     * @return a 201 Created {@link ResponseEntity} with no body
     */
    public static <T> ResponseEntity<T> created() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Returns an HTTP 201 (Created) response with a message and created data wrapped in {@link Response}.
     *
     * @param message created message
     * @param data    created payload data
     * @return a 201 Created {@link ResponseEntity} with wrapped {@link Response}
     */
    public static ResponseEntity<Response> created(String message, Object data) {
        return build(HttpStatus.CREATED, message, null, data);
    }

    /**
     * Returns an HTTP 201 (Created) response with a raw data payload.
     *
     * @param <T>  the payload type
     * @param data the created resource data
     * @return a 201 Created {@link ResponseEntity} with body
     */
    public static <T> ResponseEntity<T> created(T data) {
        return build(HttpStatus.CREATED, data);
    }

    /**
     * Returns an HTTP 201 (Created) response with a resource URI location and data payload.
     *
     * @param <T>      the payload type
     * @param location the location URI of the newly created resource
     * @param data     the created resource data
     * @return a 201 Created {@link ResponseEntity} with Location header and body
     */
    public static <T> ResponseEntity<T> created(URI location, T data) {
        return ResponseEntity.created(location).body(data);
    }

    /**
     * Returns an HTTP 201 (Created) response with headers and data payload.
     *
     * @param <T>     the payload type
     * @param headers HTTP headers
     * @param data    the created resource data
     * @return a 201 Created {@link ResponseEntity} with headers and body
     */
    public static <T> ResponseEntity<T> created(HttpHeaders headers, T data) {
        return build(HttpStatus.CREATED, headers, data);
    }

    /**
     * Returns an HTTP 202 (Accepted) response with an empty body.
     *
     * @param <T> the response body type
     * @return a 202 Accepted {@link ResponseEntity} with no body
     */
    public static <T> ResponseEntity<T> accepted() {
        return ResponseEntity.accepted().build();
    }

    /**
     * Returns an HTTP 202 (Accepted) response with a message and data wrapped in {@link Response}.
     *
     * @param message accepted message
     * @param data    payload data
     * @return a 202 Accepted {@link ResponseEntity} with wrapped {@link Response}
     */
    public static ResponseEntity<Response> accepted(String message, Object data) {
        return build(HttpStatus.ACCEPTED, message, null, data);
    }

    /**
     * Returns an HTTP 202 (Accepted) response with a raw data payload.
     *
     * @param <T>  the payload type
     * @param data the payload
     * @return a 202 Accepted {@link ResponseEntity} with body
     */
    public static <T> ResponseEntity<T> accepted(T data) {
        return build(HttpStatus.ACCEPTED, data);
    }

    /**
     * Returns an HTTP 204 (No Content) empty response.
     *
     * @return a 204 No Content {@link ResponseEntity}
     */
    public static ResponseEntity<?> noContent() {
        return ResponseEntity.noContent().build();
    }
}
