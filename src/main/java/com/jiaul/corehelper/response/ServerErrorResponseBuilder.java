package com.jiaul.corehelper.response;

import com.jiaul.corehelper.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Abstract builder providing factory methods for standard HTTP 5xx (Server Error) responses.
 */
public abstract class ServerErrorResponseBuilder extends ClientErrorResponseBuilder {

    protected ServerErrorResponseBuilder() {
        super();
    }

    // -------------------------------------------------------------
    // Server Error Responses (5xx)
    // -------------------------------------------------------------

    /**
     * Returns an HTTP 500 (Internal Server Error) response with default message "Internal Server Error".
     *
     * @return a 500 Internal Server Error {@link ResponseEntity}
     */
    public static ResponseEntity<Response> internalServerError() {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    /**
     * Returns an HTTP 500 (Internal Server Error) response with default message "Internal Server Error" and error details.
     *
     * @param error error details or error payload
     * @return a 500 Internal Server Error {@link ResponseEntity}
     */
    public static ResponseEntity<Response> internalServerError(Object error) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", error);
    }

    /**
     * Returns an HTTP 500 (Internal Server Error) response with default message "Internal Server Error", error details, and structured data.
     *
     * @param error error details or error payload
     * @param data  error details or stack trace metadata
     * @return a 500 Internal Server Error {@link ResponseEntity}
     */
    public static ResponseEntity<Response> internalServerError(Object error, Object data) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", error, data);
    }

    /**
     * Returns an HTTP 500 (Internal Server Error) response with a custom message, error details, and structured data.
     *
     * @param message custom failure message
     * @param error   error details or error payload
     * @param data    error details or stack trace metadata
     * @return a 500 Internal Server Error {@link ResponseEntity}
     */
    public static ResponseEntity<Response> internalServerError(String message, Object error, Object data) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, message, error, data);
    }
}
