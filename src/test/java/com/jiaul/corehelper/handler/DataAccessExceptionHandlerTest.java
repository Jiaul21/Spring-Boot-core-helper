package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.handler.DataAccessExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class DataAccessExceptionHandlerTest {

    private final DataAccessExceptionHandler handler = new DataAccessExceptionHandler();

    @Test
    @DisplayName("handleDataIntegrityViolation should return 409 CONFLICT with standardized Response")
    void testHandleDataIntegrityViolation() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Duplicate key value violates unique constraint");

        ResponseEntity<Response> response = handler.handleDataIntegrityViolation(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Database constraint violation");
        assertThat(response.getBody().error()).isEqualTo("Duplicate key value violates unique constraint");
    }
}

