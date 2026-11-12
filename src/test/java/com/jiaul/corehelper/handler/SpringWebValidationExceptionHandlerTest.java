package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.handler.SpringWebValidationExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringWebValidationExceptionHandlerTest {

    private final SpringWebValidationExceptionHandler handler = new SpringWebValidationExceptionHandler();

    @Test
    @DisplayName("handleHandlerMethodValidation should return 400 BAD REQUEST")
    void testHandleHandlerMethodValidation() {
        HandlerMethodValidationException ex = mock(HandlerMethodValidationException.class);
        when(ex.getMessage()).thenReturn("Validation failed for method parameter");

        ResponseEntity<Response> response = handler.handleHandlerMethodValidation(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("Request parameter validation failed");
        assertThat(response.getBody().error()).isEqualTo("Validation failed for method parameter");
    }
}

