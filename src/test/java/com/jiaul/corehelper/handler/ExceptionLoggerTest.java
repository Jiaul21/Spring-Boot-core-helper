package com.jiaul.corehelper.handler;

import com.jiaul.corehelper.exception.MethodNotAllowedException;
import com.jiaul.corehelper.handler.ExceptionLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThatCode;

class ExceptionLoggerTest {

    @Test
    @DisplayName("logException should execute cleanly for ApiException, generic exceptions, and null")
    void testLogExceptionDoesNotThrow() {
        MethodNotAllowedException apiEx = new MethodNotAllowedException("Method not allowed", "POST", null);

        assertThatCode(() -> ExceptionLogger.logException(apiEx, HttpStatus.METHOD_NOT_ALLOWED))
                .doesNotThrowAnyException();
        assertThatCode(() -> ExceptionLogger.logException(new IllegalArgumentException("Generic error"), HttpStatus.INTERNAL_SERVER_ERROR))
                .doesNotThrowAnyException();
        assertThatCode(() -> ExceptionLogger.logException(null, HttpStatus.INTERNAL_SERVER_ERROR))
                .doesNotThrowAnyException();
    }
}

