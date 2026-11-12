package com.jiaul.corehelper.config;

import com.jiaul.corehelper.exception.handler.CoreExceptionLogger;
import com.jiaul.corehelper.exception.handler.SpringWebMvcExceptionLogger;
import com.jiaul.corehelper.exception.handler.ValidationExceptionLogger;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring Boot Auto-Configuration for {@code core-helper}.
 * <p>
 * Automatically registers modular exception handlers conditionally based on
 * classpath availability and web environment type.
 * <p>
 * Handlers can be disabled globally:
 * <pre>{@code
 * core-helper.exception-handler.enabled=false
 * }</pre>
 * Or individually:
 * <pre>{@code
 * core-helper.exception-handler.core.enabled=false
 * core-helper.exception-handler.validation.enabled=false
 * core-helper.exception-handler.webmvc.enabled=false
 * }</pre>
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = "core-helper.exception-handler", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CoreHelperAutoConfiguration {

    /**
     * Registers the foundational {@link CoreExceptionLogger} for {@code ApiException},
     * {@code MethodArgumentNotValidException}, and {@code HttpMessageNotReadableException}.
     *
     * @return the default {@link CoreExceptionLogger}
     */
    @Bean
    @ConditionalOnMissingBean(CoreExceptionLogger.class)
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.core", name = "enabled", havingValue = "true", matchIfMissing = true)
    public CoreExceptionLogger coreExceptionLogger() {
        return new CoreExceptionLogger();
    }

    /**
     * Nested configuration for Jakarta Bean Validation ({@link ValidationExceptionLogger}).
     * <p>
     * Active only when {@code jakarta.validation.ConstraintViolationException} is available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "jakarta.validation.ConstraintViolationException")
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.validation", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class ValidationExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(ValidationExceptionLogger.class)
        public ValidationExceptionLogger validationExceptionLogger() {
            return new ValidationExceptionLogger();
        }
    }

    /**
     * Nested configuration for Spring 6.1+ / Spring Boot 3.2+ Web MVC exceptions ({@link SpringWebMvcExceptionLogger}).
     * <p>
     * Active only when {@code NoResourceFoundException} and {@code HandlerMethodValidationException} are available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = {
            "org.springframework.web.servlet.resource.NoResourceFoundException",
            "org.springframework.web.method.annotation.HandlerMethodValidationException"
    })
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.webmvc", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class SpringWebMvcExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(SpringWebMvcExceptionLogger.class)
        public SpringWebMvcExceptionLogger springWebMvcExceptionLogger() {
            return new SpringWebMvcExceptionLogger();
        }
    }
}
