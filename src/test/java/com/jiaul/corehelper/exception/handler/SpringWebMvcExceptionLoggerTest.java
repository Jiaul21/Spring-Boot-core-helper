package com.jiaul.corehelper.exception.handler;

import com.jiaul.corehelper.dto.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SpringWebMvcExceptionLoggerTest {

    private final SpringWebMvcExceptionLogger handler = new SpringWebMvcExceptionLogger();

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

    @Test
    @DisplayName("handleNoResourceFoundException should return 404 NOT FOUND for unmapped URLs")
    void testHandleNoResourceFoundException() {
        NoResourceFoundException ex = new NoResourceFoundException(HttpMethod.GET, "/api/non-existent");

        ResponseEntity<Response> response = handler.handleNoResourceFoundException(ex);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().message()).isEqualTo("URL does not match with any api");
        assertThat(response.getBody().error()).isNotNull();
    }
}

