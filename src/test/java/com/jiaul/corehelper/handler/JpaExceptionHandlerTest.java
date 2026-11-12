package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.handler.JpaExceptionHandler;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class JpaExceptionHandlerTest {

    private final JpaExceptionHandler handler = new JpaExceptionHandler();

    @Test
    @DisplayName("handleEntityNotFoundException should return 404 NOT FOUND with standardized Response")
    void testHandleEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Unable to find User with id 42");

        ResponseEntity<Response> response = handler.handleEntityNotFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Entity Persistence error");
        assertThat(response.getBody().error()).isEqualTo("Unable to find User with id 42");
    }
}

