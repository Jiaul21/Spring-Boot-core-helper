package com.jiaul.corehelper.response;

/**
 * Standard utility class for constructing uniform {@link org.springframework.http.ResponseEntity} objects.
 * <p>
 * Combines standard response factory methods from {@link ResponseBuilder}, {@link SuccessResponseBuilder},
 * {@link ClientErrorResponseBuilder}, and {@link ServerErrorResponseBuilder}.
 */
public final class ApiResponseBuilder extends ServerErrorResponseBuilder {

    private ApiResponseBuilder() {
        // Prevent direct instantiation
    }
}
