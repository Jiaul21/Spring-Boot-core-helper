package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.dto.Response;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationExceptionLoggerTest {

    private final ValidationExceptionLogger handler = new ValidationExceptionLogger();

    @Test
    @DisplayName("handleConstraintViolations should format property violations into 400 BAD REQUEST")
    void testHandleConstraintViolations() {
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("username");
        when(violation.getPropertyPath()).thenReturn(path);
        when(violation.getMessage()).thenReturn("Size must be between 3 and 20");

        ConstraintViolationException ex = new ConstraintViolationException("Invalid", Set.of(violation));

        ResponseEntity<Response> response = handler.handleConstraintViolations(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Constraint violation");
        assertThat(response.getBody().error()).isInstanceOf(Map.class);
        @SuppressWarnings("unchecked")
        Map<String, String> errors = (Map<String, String>) response.getBody().error();
        assertThat(errors).containsEntry("username", "Size must be between 3 and 20");
    }
}

