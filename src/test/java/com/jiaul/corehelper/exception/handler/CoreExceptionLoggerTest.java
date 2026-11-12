package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.exception.BadRequestException;
import com.jiaul.corehelper.exception.MethodNotAllowedException;
import com.jiaul.corehelper.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class CoreExceptionLoggerTest {

    private final CoreExceptionLogger handler = new CoreExceptionLogger();

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

    void dummyValidationMethod(String email) {
    }

    @Test
    @DisplayName("handleValidationExceptions should format field errors into 400 BAD REQUEST")
    void testHandleValidationExceptions() throws Exception {
        java.lang.reflect.Method method = CoreExceptionLoggerTest.class.getDeclaredMethod("dummyValidationMethod", String.class);
        org.springframework.core.MethodParameter parameter = new org.springframework.core.MethodParameter(method, 0);

        BindException bindException = new BindException(new Object(), "target");
        bindException.addError(new FieldError("target", "email", "Email cannot be blank"));
        bindException.addError(new FieldError("target", "age", "Must be 18 or older"));
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(parameter, bindException);

        ResponseEntity<Response> response = handler.handleValidationExceptions(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Validation failed");
        assertThat(response.getBody().error()).isInstanceOf(Map.class);
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) response.getBody().error();
        assertThat(errors)
                .containsEntry("email", "Email cannot be blank")
                .containsEntry("age", "Must be 18 or older");
    }

    @Test
    @DisplayName("handleHttpMessageNotReadable should return 400 BAD REQUEST for malformed JSON")
    void testHandleHttpMessageNotReadable() {
        HttpMessageNotReadableException ex = new HttpMessageNotReadableException("JSON parse error: Unexpected character");

        ResponseEntity<Response> response = handler.handleHttpMessageNotReadable(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Request body missing or malformed");
        assertThat(response.getBody().error().toString()).contains("JSON parse error");
    }

    @Test
    @DisplayName("logException should execute without throwing any exception")
    void testLogExceptionDoesNotThrow() {
        MethodNotAllowedException ex = new MethodNotAllowedException("Method not allowed", "POST", null);
        handler.logException(ex, HttpStatus.METHOD_NOT_ALLOWED);

        handler.logException(new IllegalArgumentException("Generic error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

