package com.jiaul.corehelper.config;

import com.jiaul.corehelper.exception.handler.CoreExceptionLogger;
import com.jiaul.corehelper.exception.handler.SpringWebMvcExceptionLogger;
import com.jiaul.corehelper.exception.handler.ValidationExceptionLogger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoreHelperAutoConfigurationTest {

    @Test
    @DisplayName("coreExceptionLogger should produce non-null instance")
    void testCoreExceptionLoggerBeanCreation() {
        CoreHelperAutoConfiguration config = new CoreHelperAutoConfiguration();
        CoreExceptionLogger handler = config.coreExceptionLogger();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("validationExceptionLogger should produce non-null instance")
    void testValidationExceptionLoggerBeanCreation() {
        CoreHelperAutoConfiguration.ValidationExceptionConfiguration config =
                new CoreHelperAutoConfiguration.ValidationExceptionConfiguration();
        ValidationExceptionLogger handler = config.validationExceptionLogger();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("springWebMvcExceptionLogger should produce non-null instance")
    void testSpringWebMvcExceptionLoggerBeanCreation() {
        CoreHelperAutoConfiguration.SpringWebMvcExceptionConfiguration config =
                new CoreHelperAutoConfiguration.SpringWebMvcExceptionConfiguration();
        SpringWebMvcExceptionLogger handler = config.springWebMvcExceptionLogger();

        assertThat(handler).isNotNull();
    }
}
