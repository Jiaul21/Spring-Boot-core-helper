package com.jiaul.corehelper.config;

import com.jiaul.corehelper.exception.CoreExceptionHandler;
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
}
