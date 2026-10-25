package com.jiaul.corehelper.exception;

import com.jiaul.corehelper.dto.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CoreExceptionHandlerTest {

    private final CoreExceptionHandler handler = new CoreExceptionHandler();

    @Test
    @DisplayName("handleApiException should transform BadRequestException to standard 400 response")
    void testHandleBadRequestException() {
        BadRequestException ex = new BadRequestException("Invalid input", "Validation failed", Map.of("field", "email"));

        ResponseEntity<Response> response = handler.handleApiException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Invalid input");
        assertThat(response.getBody().error()).isEqualTo("Validation failed");
        assertThat(response.getBody().data()).isEqualTo(Map.of("field", "email"));
        assertThat(response.getBody().timestamp()).isNotNull();
    }

    @Test
    @DisplayName("handleApiException should handle exception with null error and null data")
    void testHandleExceptionWithNulls() {
        NotFoundException ex = new NotFoundException("Resource not found");

        ResponseEntity<Response> response = handler.handleApiException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Resource not found");
        assertThat(response.getBody().error()).isNull();
        assertThat(response.getBody().data()).isNull();
    }

    @Test
    @DisplayName("logException should execute without throwing any exception")
    void testLogExceptionDoesNotThrow() {
        MethodNotAllowedException ex = new MethodNotAllowedException("Method not allowed", "POST", null);

        handler.logException(ex);
    }
}
