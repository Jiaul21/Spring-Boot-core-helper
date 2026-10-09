package com.jiaul.corehelper.response;

import com.jiaul.corehelper.dto.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseBuilderTest {

    @Test
    @DisplayName("ok() should return 200 with empty body")
    void testOkDefault() {
        ResponseEntity<Void> response = ApiResponseBuilder.ok();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("ok(message, data) should return 200 with message and wrapped data")
    void testOkMessageAndData() {
        Map<String, String> payload = Map.of("key", "value");
        ResponseEntity<Response> response = ApiResponseBuilder.ok("User retrieved", payload);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("User retrieved");
        assertThat(response.getBody().data()).isEqualTo(payload);
        assertThat(response.getBody().error()).isNull();
        assertThat(response.getBody().timestamp()).isNotNull();
    }

    @Test
    @DisplayName("ok(data) should return 200 with raw payload")
    void testOkWithData() {
        Map<String, String> payload = Map.of("key", "value");
        ResponseEntity<Map<String, String>> response = ApiResponseBuilder.ok(payload);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(payload);
    }

    @Test
    @DisplayName("ok(headers, data) should return 200 with headers and raw payload")
    void testOkWithHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Custom-Header", "test-val");
        ResponseEntity<String> response = ApiResponseBuilder.ok(headers, "body");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getFirst("X-Custom-Header")).isEqualTo("test-val");
        assertThat(response.getBody()).isEqualTo("body");
    }

    @Test
    @DisplayName("created() should return 201 with empty body")
    void testCreatedDefault() {
        ResponseEntity<Void> response = ApiResponseBuilder.created();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("created(message, data) should return 201 with wrapped data")
    void testCreatedMessageAndData() {
        Map<String, String> data = Map.of("id", "item-123");
        ResponseEntity<Response> response = ApiResponseBuilder.created("Created item", data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Created item");
        assertThat(response.getBody().data()).isEqualTo(data);
    }

    @Test
    @DisplayName("created(data) should return 201 with raw payload")
    void testCreatedWithData() {
        Map<String, String> data = Map.of("id", "item-123");
        ResponseEntity<Map<String, String>> response = ApiResponseBuilder.created(data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(data);
    }

    @Test
    @DisplayName("created(location, data) should return 201 with Location header")
    void testCreatedWithLocation() {
        URI location = URI.create("/api/v1/items/42");
        Map<String, String> data = Map.of("id", "42");
        ResponseEntity<Map<String, String>> response = ApiResponseBuilder.created(location, data);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getHeaders().getLocation()).isEqualTo(location);
        assertThat(response.getBody()).isEqualTo(data);
    }

    @Test
    @DisplayName("accepted() should return 202 with empty body")
    void testAccepted() {
        ResponseEntity<Void> response = ApiResponseBuilder.accepted();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("noContent() should return 204 with no body")
    void testNoContent() {
        ResponseEntity<?> response = ApiResponseBuilder.noContent();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    @DisplayName("badRequest() with default message, error details, and data")
    void testBadRequest() {
        Map<String, String> errors = Map.of("email", "must be valid");
        ResponseEntity<Response> response = ApiResponseBuilder.badRequest("Validation failed", errors);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Bad Request");
        assertThat(response.getBody().error()).isEqualTo("Validation failed");
        assertThat(response.getBody().data()).isEqualTo(errors);
        assertThat(response.getBody().timestamp()).isNotNull();
    }

    @Test
    @DisplayName("badRequest() with custom message, error details, and data")
    void testBadRequestWithCustomMessage() {
        Map<String, String> errors = Map.of("email", "must be valid");
        ResponseEntity<Response> response = ApiResponseBuilder.badRequest("Invalid input", "Validation failed", errors);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Invalid input");
        assertThat(response.getBody().error()).isEqualTo("Validation failed");
        assertThat(response.getBody().data()).isEqualTo(errors);
        assertThat(response.getBody().timestamp()).isNotNull();
    }

    @Test
    @DisplayName("unauthorized() should return 401 with default message and error")
    void testUnauthorized() {
        ResponseEntity<Response> response = ApiResponseBuilder.unauthorized("Token expired");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Unauthorized Request");
        assertThat(response.getBody().error()).isEqualTo("Token expired");
        assertThat(response.getBody().data()).isNull();
    }

    @Test
    @DisplayName("forbidden() should return 403 with default message and error")
    void testForbidden() {
        ResponseEntity<Response> response = ApiResponseBuilder.forbidden("Access denied");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Forbidden Request");
        assertThat(response.getBody().error()).isEqualTo("Access denied");
    }

    @Test
    @DisplayName("notFound() should return 404 with default message and error")
    void testNotFound() {
        ResponseEntity<Response> response = ApiResponseBuilder.notFound("User not found");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Resource Not Found");
        assertThat(response.getBody().error()).isEqualTo("User not found");
    }

    @Test
    @DisplayName("conflict() should return 409 with default message, error, and data")
    void testConflict() {
        ResponseEntity<Response> response = ApiResponseBuilder.conflict("Already exists", Map.of("id", "123"));

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Conflict");
        assertThat(response.getBody().error()).isEqualTo("Already exists");
        assertThat(response.getBody().data()).isEqualTo(Map.of("id", "123"));
    }

    @Test
    @DisplayName("unprocessableEntity() should return 422 with custom message, error, and data")
    void testUnprocessableEntity() {
        ResponseEntity<Response> response = ApiResponseBuilder.unprocessableEntity("Validation Error", "Invalid range", "date_range");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Validation Error");
        assertThat(response.getBody().error()).isEqualTo("Invalid range");
        assertThat(response.getBody().data()).isEqualTo("date_range");
    }

    @Test
    @DisplayName("tooManyRequests() should return 429 with error and data")
    void testTooManyRequests() {
        ResponseEntity<Response> response = ApiResponseBuilder.tooManyRequests("Limit exceeded", "Try again in 60s");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Too Many Requests");
        assertThat(response.getBody().error()).isEqualTo("Limit exceeded");
        assertThat(response.getBody().data()).isEqualTo("Try again in 60s");
    }

    @Test
    @DisplayName("internalServerError() should return 500 with default message and error")
    void testInternalServerError() {
        ResponseEntity<Response> response = ApiResponseBuilder.internalServerError("Server crashed");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Internal Server Error");
        assertThat(response.getBody().error()).isEqualTo("Server crashed");
    }

    @Test
    @DisplayName("build() should support custom status and message")
    void testBuildStatusAndMessage() {
        ResponseEntity<Response> response = ApiResponseBuilder.build(HttpStatus.OK, "Operation completed");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Operation completed");
        assertThat(response.getBody().error()).isNull();
        assertThat(response.getBody().data()).isNull();
    }

    @Test
    @DisplayName("build() should support custom status, message, and error")
    void testBuildStatusMessageAndError() {
        ResponseEntity<Response> response = ApiResponseBuilder.build(HttpStatus.BAD_GATEWAY, "Gateway Failure", "Connection timed out");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_GATEWAY);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Gateway Failure");
        assertThat(response.getBody().error()).isEqualTo("Connection timed out");
        assertThat(response.getBody().data()).isNull();
    }

    @Test
    @DisplayName("build() should support custom status, message, error, and data")
    void testCustomBuild() {
        ResponseEntity<Response> response = ApiResponseBuilder.build(HttpStatus.I_AM_A_TEAPOT, "Teapot", "Brewing error", null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.I_AM_A_TEAPOT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Teapot");
        assertThat(response.getBody().error()).isEqualTo("Brewing error");
    }

    @Test
    @DisplayName("build() should support custom status, headers, and raw data")
    void testBuildWithHeadersAndData() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Custom", "value");
        ResponseEntity<String> response = ApiResponseBuilder.build(HttpStatus.ACCEPTED, headers, "Processed");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.ACCEPTED);
        assertThat(response.getHeaders().getFirst("X-Custom")).isEqualTo("value");
        assertThat(response.getBody()).isEqualTo("Processed");
    }
}
