package com.jiaul.corehelper.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

/**
 * Standard API response wrapper record for API messages, errors, and payload data.
 * <p>
 * Null fields are excluded from JSON serialization.
 *
 * @param message   an informational or status message
 * @param error     error details, failure description, or structured error object
 * @param data      payload data, response body, or additional metadata
 * @param timestamp the UTC timestamp when the response was created
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response(
        String message,
        Object error,
        Object data,
        Instant timestamp
) {

    /**
     * Constructs a {@code Response} with a message, error details, payload data, and current UTC {@link Instant}.
     *
     * @param message an informational or status message
     * @param error   error details or failure description
     * @param data    payload data or response metadata
     */
    public Response(String message, Object error, Object data) {
        this(message, error, data, Instant.now());
    }

    /**
     * Constructs a {@code Response} with a message, error details, and current UTC {@link Instant}.
     *
     * @param message an informational or status message
     * @param error   error details or failure description
     */
    public Response(String message, Object error) {
        this(message, error, null, Instant.now());
    }

    /**
     * Constructs a {@code Response} with only a message and current UTC {@link Instant}.
     *
     * @param message an informational or status message
     */
    public Response(String message) {
        this(message, null, null, Instant.now());
    }
}
