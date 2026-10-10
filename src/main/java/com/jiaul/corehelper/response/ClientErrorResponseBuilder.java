package com.jiaul.corehelper.response;

import com.jiaul.corehelper.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Abstract builder providing factory methods for standard HTTP 4xx (Client Error) responses.
 * <p>
 * Requires at least an error details object so every client error communicates meaningful failure context.
 */
public abstract class ClientErrorResponseBuilder extends SuccessResponseBuilder {

    protected ClientErrorResponseBuilder() {
        super();
    }

    // -------------------------------------------------------------
    // Client Error Responses (4xx)
    // -------------------------------------------------------------

    /**
     * Returns an HTTP 400 (Bad Request) response with default message "Bad Request" and error details.
     *
     * @param error error details or error payload
     * @return a 400 Bad Request {@link ResponseEntity}
     */
    public static ResponseEntity<Response> badRequest(Object error) {
        return build(HttpStatus.BAD_REQUEST, "Bad Request", error);
    }

    /**
     * Returns an HTTP 400 (Bad Request) response with default message "Bad Request", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  structured payload data or field errors
     * @return a 400 Bad Request {@link ResponseEntity}
     */
    public static ResponseEntity<Response> badRequest(Object error, Object data) {
        return build(HttpStatus.BAD_REQUEST, "Bad Request", error, data);
    }

    /**
     * Returns an HTTP 400 (Bad Request) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    structured payload data or field errors
     * @return a 400 Bad Request {@link ResponseEntity}
     */
    public static ResponseEntity<Response> badRequest(String message, Object error, Object data) {
        return build(HttpStatus.BAD_REQUEST, message, error, data);
    }

    /**
     * Returns an HTTP 401 (Unauthorized) response with default message "Unauthorized Request" and error details.
     *
     * @param error error details or error payload
     * @return a 401 Unauthorized {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unauthorized(Object error) {
        return build(HttpStatus.UNAUTHORIZED, "Unauthorized Request", error);
    }

    /**
     * Returns an HTTP 401 (Unauthorized) response with default message "Unauthorized Request", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  structured auth data or metadata
     * @return a 401 Unauthorized {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unauthorized(Object error, Object data) {
        return build(HttpStatus.UNAUTHORIZED, "Unauthorized Request", error, data);
    }

    /**
     * Returns an HTTP 401 (Unauthorized) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    structured auth data or metadata
     * @return a 401 Unauthorized {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unauthorized(String message, Object error, Object data) {
        return build(HttpStatus.UNAUTHORIZED, message, error, data);
    }

    /**
     * Returns an HTTP 403 (Forbidden) response with default message "Forbidden Request" and error details.
     *
     * @param error error details or error payload
     * @return a 403 Forbidden {@link ResponseEntity}
     */
    public static ResponseEntity<Response> forbidden(Object error) {
        return build(HttpStatus.FORBIDDEN, "Forbidden Request", error);
    }

    /**
     * Returns an HTTP 403 (Forbidden) response with default message "Forbidden Request", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  structured permission details
     * @return a 403 Forbidden {@link ResponseEntity}
     */
    public static ResponseEntity<Response> forbidden(Object error, Object data) {
        return build(HttpStatus.FORBIDDEN, "Forbidden Request", error, data);
    }

    /**
     * Returns an HTTP 403 (Forbidden) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    structured permission details
     * @return a 403 Forbidden {@link ResponseEntity}
     */
    public static ResponseEntity<Response> forbidden(String message, Object error, Object data) {
        return build(HttpStatus.FORBIDDEN, message, error, data);
    }

    /**
     * Returns an HTTP 404 (Not Found) response with default message "Resource Not Found" and error details.
     *
     * @param error error details or error payload
     * @return a 404 Not Found {@link ResponseEntity}
     */
    public static ResponseEntity<Response> notFound(Object error) {
        return build(HttpStatus.NOT_FOUND, "Resource Not Found", error);
    }

    /**
     * Returns an HTTP 404 (Not Found) response with default message "Resource Not Found", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  structured details
     * @return a 404 Not Found {@link ResponseEntity}
     */
    public static ResponseEntity<Response> notFound(Object error, Object data) {
        return build(HttpStatus.NOT_FOUND, "Resource Not Found", error, data);
    }

    /**
     * Returns an HTTP 404 (Not Found) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    structured details
     * @return a 404 Not Found {@link ResponseEntity}
     */
    public static ResponseEntity<Response> notFound(String message, Object error, Object data) {
        return build(HttpStatus.NOT_FOUND, message, error, data);
    }

    /**
     * Returns an HTTP 409 (Conflict) response with default message "Conflict" and error details.
     *
     * @param error error details or error payload
     * @return a 409 Conflict {@link ResponseEntity}
     */
    public static ResponseEntity<Response> conflict(Object error) {
        return build(HttpStatus.CONFLICT, "Conflict", error);
    }

    /**
     * Returns an HTTP 409 (Conflict) response with default message "Conflict", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  conflict data or details
     * @return a 409 Conflict {@link ResponseEntity}
     */
    public static ResponseEntity<Response> conflict(Object error, Object data) {
        return build(HttpStatus.CONFLICT, "Conflict", error, data);
    }

    /**
     * Returns an HTTP 409 (Conflict) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    conflict data or details
     * @return a 409 Conflict {@link ResponseEntity}
     */
    public static ResponseEntity<Response> conflict(String message, Object error, Object data) {
        return build(HttpStatus.CONFLICT, message, error, data);
    }

    /**
     * Returns an HTTP 422 (Unprocessable Entity) response with default message "Unprocessable Entity" and error details.
     *
     * @param error semantic error details or error payload
     * @return a 422 Unprocessable Entity {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unprocessableEntity(Object error) {
        return build(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity", error);
    }

    /**
     * Returns an HTTP 422 (Unprocessable Entity) response with default message "Unprocessable Entity", error details, and structured data.
     *
     * @param error semantic error details or error payload
     * @param data  validation errors or details
     * @return a 422 Unprocessable Entity {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unprocessableEntity(Object error, Object data) {
        return build(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity", error, data);
    }

    /**
     * Returns an HTTP 422 (Unprocessable Entity) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   semantic error details or error payload
     * @param data    validation errors or details
     * @return a 422 Unprocessable Entity {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unprocessableEntity(String message, Object error, Object data) {
        return build(HttpStatus.UNPROCESSABLE_ENTITY, message, error, data);
    }

    /**
     * Returns an HTTP 429 (Too Many Requests) response with default message "Too Many Requests" and error details.
     *
     * @param error error details or error payload
     * @return a 429 Too Many Requests {@link ResponseEntity}
     */
    public static ResponseEntity<Response> tooManyRequests(Object error) {
        return build(HttpStatus.TOO_MANY_REQUESTS, "Too Many Requests", error);
    }

    /**
     * Returns an HTTP 429 (Too Many Requests) response with default message "Too Many Requests", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  quota/limit details
     * @return a 429 Too Many Requests {@link ResponseEntity}
     */
    public static ResponseEntity<Response> tooManyRequests(Object error, Object data) {
        return build(HttpStatus.TOO_MANY_REQUESTS, "Too Many Requests", error, data);
    }

    /**
     * Returns an HTTP 429 (Too Many Requests) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    quota/limit details
     * @return a 429 Too Many Requests {@link ResponseEntity}
     */
    public static ResponseEntity<Response> tooManyRequests(String message, Object error, Object data) {
        return build(HttpStatus.TOO_MANY_REQUESTS, message, error, data);
    }

    /**
     * Returns an HTTP 405 (Method Not Allowed) response with default message "Method Not Allowed" and error details.
     *
     * @param error error details or error payload
     * @return a 405 Method Not Allowed {@link ResponseEntity}
     */
    public static ResponseEntity<Response> methodNotAllowed(Object error) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed", error);
    }

    /**
     * Returns an HTTP 405 (Method Not Allowed) response with default message "Method Not Allowed", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  allowed methods or metadata
     * @return a 405 Method Not Allowed {@link ResponseEntity}
     */
    public static ResponseEntity<Response> methodNotAllowed(Object error, Object data) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, "Method Not Allowed", error, data);
    }

    /**
     * Returns an HTTP 405 (Method Not Allowed) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    allowed methods or metadata
     * @return a 405 Method Not Allowed {@link ResponseEntity}
     */
    public static ResponseEntity<Response> methodNotAllowed(String message, Object error, Object data) {
        return build(HttpStatus.METHOD_NOT_ALLOWED, message, error, data);
    }

    /**
     * Returns an HTTP 408 (Request Timeout) response with default message "Request Timeout" and error details.
     *
     * @param error error details or error payload
     * @return a 408 Request Timeout {@link ResponseEntity}
     */
    public static ResponseEntity<Response> requestTimeout(Object error) {
        return build(HttpStatus.REQUEST_TIMEOUT, "Request Timeout", error);
    }

    /**
     * Returns an HTTP 408 (Request Timeout) response with default message "Request Timeout", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  timeout metadata or details
     * @return a 408 Request Timeout {@link ResponseEntity}
     */
    public static ResponseEntity<Response> requestTimeout(Object error, Object data) {
        return build(HttpStatus.REQUEST_TIMEOUT, "Request Timeout", error, data);
    }

    /**
     * Returns an HTTP 408 (Request Timeout) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    timeout metadata or details
     * @return a 408 Request Timeout {@link ResponseEntity}
     */
    public static ResponseEntity<Response> requestTimeout(String message, Object error, Object data) {
        return build(HttpStatus.REQUEST_TIMEOUT, message, error, data);
    }

    /**
     * Returns an HTTP 415 (Unsupported Media Type) response with default message "Unsupported Media Type" and error details.
     *
     * @param error error details or error payload
     * @return a 415 Unsupported Media Type {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unsupportedMediaType(Object error) {
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type", error);
    }

    /**
     * Returns an HTTP 415 (Unsupported Media Type) response with default message "Unsupported Media Type", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  supported media types or metadata
     * @return a 415 Unsupported Media Type {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unsupportedMediaType(Object error, Object data) {
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type", error, data);
    }

    /**
     * Returns an HTTP 415 (Unsupported Media Type) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    supported media types or metadata
     * @return a 415 Unsupported Media Type {@link ResponseEntity}
     */
    public static ResponseEntity<Response> unsupportedMediaType(String message, Object error, Object data) {
        return build(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message, error, data);
    }
}
