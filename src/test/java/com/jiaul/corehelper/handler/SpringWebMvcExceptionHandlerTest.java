package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.handler.SpringWebMvcExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.assertj.core.api.Assertions.assertThat;

class SpringWebMvcExceptionHandlerTest {

    private final SpringWebMvcExceptionHandler handler = new SpringWebMvcExceptionHandler();

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

