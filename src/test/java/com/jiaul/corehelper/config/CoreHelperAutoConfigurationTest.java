package com.jiaul.corehelper.config;

import com.jiaul.corehelper.handler.CoreExceptionHandler;
import com.jiaul.corehelper.handler.DataAccessExceptionHandler;
import com.jiaul.corehelper.handler.JpaExceptionHandler;
import com.jiaul.corehelper.handler.SpringWebExceptionHandler;
import com.jiaul.corehelper.handler.SpringWebMvcExceptionHandler;
import com.jiaul.corehelper.handler.SpringWebValidationExceptionHandler;
import com.jiaul.corehelper.handler.ValidationExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoreHelperAutoConfigurationTest {

    @Test
    @DisplayName("coreExceptionHandler should produce non-null instance")
    void testCoreExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration config = new CoreHelperAutoConfiguration();
        CoreExceptionHandler handler = config.coreExceptionHandler();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("springWebExceptionHandler should produce non-null instance")
    void testSpringWebExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration.SpringWebExceptionConfiguration config =
                new CoreHelperAutoConfiguration.SpringWebExceptionConfiguration();
        SpringWebExceptionHandler handler = config.springWebExceptionHandler();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("springWebValidationExceptionHandler should produce non-null instance")
    void testSpringWebValidationExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration.SpringWebValidationExceptionConfiguration config =
                new CoreHelperAutoConfiguration.SpringWebValidationExceptionConfiguration();
        SpringWebValidationExceptionHandler handler = config.springWebValidationExceptionHandler();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("validationExceptionHandler should produce non-null instance")
    void testValidationExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration.ValidationExceptionConfiguration config =
                new CoreHelperAutoConfiguration.ValidationExceptionConfiguration();
        ValidationExceptionHandler handler = config.validationExceptionHandler();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("springWebMvcExceptionHandler should produce non-null instance")
    void testSpringWebMvcExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration.SpringWebMvcExceptionConfiguration config =
                new CoreHelperAutoConfiguration.SpringWebMvcExceptionConfiguration();
        SpringWebMvcExceptionHandler handler = config.springWebMvcExceptionHandler();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("dataAccessExceptionHandler should produce non-null instance")
    void testDataAccessExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration.DataAccessExceptionConfiguration config =
                new CoreHelperAutoConfiguration.DataAccessExceptionConfiguration();
        DataAccessExceptionHandler handler = config.dataAccessExceptionHandler();

        assertThat(handler).isNotNull();
    }

    @Test
    @DisplayName("jpaExceptionHandler should produce non-null instance")
    void testJpaExceptionHandlerBeanCreation() {
        CoreHelperAutoConfiguration.JpaExceptionConfiguration config =
                new CoreHelperAutoConfiguration.JpaExceptionConfiguration();
        JpaExceptionHandler handler = config.jpaExceptionHandler();

        assertThat(handler).isNotNull();
    }
}
