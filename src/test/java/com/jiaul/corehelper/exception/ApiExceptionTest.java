package com.jiaul.corehelper.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApiExceptionTest {

    @Test
    @DisplayName("BadRequestException maps to 400 and preserves message, error, and data")
    void testBadRequestException() {
        Map<String, String> data = Map.of("field", "invalid");
        BadRequestException ex = new BadRequestException("Bad input", "Validation failed", data);

        assertThat(ex.getMessage()).isEqualTo("Bad input");
        assertThat(ex.getError()).isEqualTo("Validation failed");
        assertThat(ex.getData()).isEqualTo(data);
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @DisplayName("UnauthorizedException maps to 401 and preserves error")
    void testUnauthorizedException() {
        UnauthorizedException ex = new UnauthorizedException("Unauthorized", "Token expired");

        assertThat(ex.getMessage()).isEqualTo("Unauthorized");
        assertThat(ex.getError()).isEqualTo("Token expired");
        assertThat(ex.getData()).isNull();
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    @DisplayName("ForbiddenException maps to 403")
    void testForbiddenException() {
        ForbiddenException ex = new ForbiddenException("Forbidden access");

        assertThat(ex.getMessage()).isEqualTo("Forbidden access");
        assertThat(ex.getError()).isNull();
        assertThat(ex.getData()).isNull();
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.FORBIDDEN);
    }


    @Test
    @DisplayName("NotFoundException maps to 404")
    void testNotFoundException() {
        NotFoundException ex = new NotFoundException("Not found", "Resource with id 123 does not exist");

        assertThat(ex.getMessage()).isEqualTo("Not found");
        assertThat(ex.getError()).isEqualTo("Resource with id 123 does not exist");
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("ConflictException maps to 409")
    void testConflictException() {
        ConflictException ex = new ConflictException("Conflict occurred", "Username taken", Map.of("username", "john"));

        assertThat(ex.getMessage()).isEqualTo("Conflict occurred");
        assertThat(ex.getError()).isEqualTo("Username taken");
        assertThat(ex.getData()).isEqualTo(Map.of("username", "john"));
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("UnprocessableEntityException maps to 422")
    void testUnprocessableEntityException() {
        UnprocessableEntityException ex = new UnprocessableEntityException("Semantic error", "Dates do not match", "date_range");

        assertThat(ex.getMessage()).isEqualTo("Semantic error");
        assertThat(ex.getError()).isEqualTo("Dates do not match");
        assertThat(ex.getData()).isEqualTo("date_range");
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    @DisplayName("TooManyRequestsException maps to 429")
    void testTooManyRequestsException() {
        TooManyRequestsException ex = new TooManyRequestsException("Rate limited", "Quota exceeded", "60s");

        assertThat(ex.getMessage()).isEqualTo("Rate limited");
        assertThat(ex.getError()).isEqualTo("Quota exceeded");
        assertThat(ex.getData()).isEqualTo("60s");
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);
    }

    @Test
    @DisplayName("MethodNotAllowedException maps to 405")
    void testMethodNotAllowedException() {
        MethodNotAllowedException ex = new MethodNotAllowedException("Method not allowed", "POST not supported", Map.of("allowed", "GET"));

        assertThat(ex.getMessage()).isEqualTo("Method not allowed");
        assertThat(ex.getError()).isEqualTo("POST not supported");
        assertThat(ex.getData()).isEqualTo(Map.of("allowed", "GET"));
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    @DisplayName("RequestTimeoutException maps to 408")
    void testRequestTimeoutException() {
        RequestTimeoutException ex = new RequestTimeoutException("Timeout", "Gateway timed out", "5000ms");

        assertThat(ex.getMessage()).isEqualTo("Timeout");
        assertThat(ex.getError()).isEqualTo("Gateway timed out");
        assertThat(ex.getData()).isEqualTo("5000ms");
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.REQUEST_TIMEOUT);
    }

    @Test
    @DisplayName("UnsupportedMediaTypeException maps to 415")
    void testUnsupportedMediaTypeException() {
        UnsupportedMediaTypeException ex = new UnsupportedMediaTypeException("Unsupported media", "XML not supported", "application/xml");

        assertThat(ex.getMessage()).isEqualTo("Unsupported media");
        assertThat(ex.getError()).isEqualTo("XML not supported");
        assertThat(ex.getData()).isEqualTo("application/xml");
        assertThat(ex.getHttpStatus()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @Test
    @DisplayName("Exception cause chaining and 4-argument constructor work properly")
    void testExceptionChaining() {
        IllegalArgumentException cause = new IllegalArgumentException("Root cause");
        BadRequestException ex = new BadRequestException("Bad request", "Invalid param", Map.of("p", 1), cause);

        assertThat(ex.getMessage()).isEqualTo("Bad request");
        assertThat(ex.getError()).isEqualTo("Invalid param");
        assertThat(ex.getData()).isEqualTo(Map.of("p", 1));
        assertThat(ex.getCause()).isSameAs(cause);
    }
}
