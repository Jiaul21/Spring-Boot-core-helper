package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.dto.Response;
import com.jiaul.corehelper.handler.SpringWebExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class SpringWebExceptionHandlerTest {

    private final SpringWebExceptionHandler handler = new SpringWebExceptionHandler();

    void dummyValidationMethod(String email) {
    }

    @Test
    @DisplayName("handleValidationExceptions should format field errors into 400 BAD REQUEST")
    void testHandleValidationExceptions() throws Exception {
        Method method = SpringWebExceptionHandlerTest.class.getDeclaredMethod("dummyValidationMethod", String.class);
        MethodParameter parameter = new MethodParameter(method, 0);

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
}

