package com.jiaul.corehelper.config;

import com.jiaul.corehelper.handler.CoreExceptionHandler;
import com.jiaul.corehelper.handler.DataAccessExceptionHandler;
import com.jiaul.corehelper.handler.JpaExceptionHandler;
import com.jiaul.corehelper.handler.SpringWebExceptionHandler;
import com.jiaul.corehelper.handler.SpringWebMvcExceptionHandler;
import com.jiaul.corehelper.handler.SpringWebValidationExceptionHandler;
import com.jiaul.corehelper.handler.ValidationExceptionHandler;
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
 * core-helper.exception-handler.web.enabled=false
 * core-helper.exception-handler.web-validation.enabled=false
 * core-helper.exception-handler.webmvc.enabled=false
 * core-helper.exception-handler.validation.enabled=false
 * core-helper.exception-handler.data-access.enabled=false
 * core-helper.exception-handler.jpa.enabled=false
 * }</pre>
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = "core-helper.exception-handler", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CoreHelperAutoConfiguration {

    /**
     * Registers the foundational {@link CoreExceptionHandler} for {@code ApiException}.
     *
     * @return the default {@link CoreExceptionHandler}
     */
    @Bean
    @ConditionalOnMissingBean(CoreExceptionHandler.class)
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.core", name = "enabled", havingValue = "true", matchIfMissing = true)
    public CoreExceptionHandler coreExceptionHandler() {
        return new CoreExceptionHandler();
    }

    /**
     * Nested configuration for standard Spring Web ({@code spring-web}) exceptions ({@link SpringWebExceptionHandler}).
     * <p>
     * Active only when {@code MethodArgumentNotValidException} and {@code HttpMessageNotReadableException}
     * are available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = {
            "org.springframework.web.bind.MethodArgumentNotValidException",
            "org.springframework.http.converter.HttpMessageNotReadableException"
    })
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.web", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class SpringWebExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(SpringWebExceptionHandler.class)
        public SpringWebExceptionHandler springWebExceptionHandler() {
            return new SpringWebExceptionHandler();
        }
    }

    /**
     * Nested configuration for Spring Web 6.1+ / Spring Boot 3.2+ method validation ({@link SpringWebValidationExceptionHandler}).
     * <p>
     * Active only when {@code org.springframework.web.method.annotation.HandlerMethodValidationException}
     * from {@code spring-web} is available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "org.springframework.web.method.annotation.HandlerMethodValidationException")
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.web-validation", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class SpringWebValidationExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(SpringWebValidationExceptionHandler.class)
        public SpringWebValidationExceptionHandler springWebValidationExceptionHandler() {
            return new SpringWebValidationExceptionHandler();
        }
    }

    /**
     * Nested configuration for Jakarta Bean Validation ({@link ValidationExceptionHandler}).
     * <p>
     * Active only when {@code jakarta.validation.ConstraintViolationException} is available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "jakarta.validation.ConstraintViolationException")
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.validation", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class ValidationExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(ValidationExceptionHandler.class)
        public ValidationExceptionHandler validationExceptionHandler() {
            return new ValidationExceptionHandler();
        }
    }

    /**
     * Nested configuration for Spring Web MVC 6.1+ / Spring Boot 3.2+ 404 exceptions ({@link SpringWebMvcExceptionHandler}).
     * <p>
     * Active only when {@code org.springframework.web.servlet.resource.NoResourceFoundException}
     * from {@code spring-webmvc} is available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "org.springframework.web.servlet.resource.NoResourceFoundException")
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.webmvc", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class SpringWebMvcExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(SpringWebMvcExceptionHandler.class)
        public SpringWebMvcExceptionHandler springWebMvcExceptionHandler() {
            return new SpringWebMvcExceptionHandler();
        }
    }

    /**
     * Nested configuration for Spring Data Access exceptions ({@link DataAccessExceptionHandler}).
     * <p>
     * Active only when {@code org.springframework.dao.DataIntegrityViolationException} is available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "org.springframework.dao.DataIntegrityViolationException")
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.data-access", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class DataAccessExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(DataAccessExceptionHandler.class)
        public DataAccessExceptionHandler dataAccessExceptionHandler() {
            return new DataAccessExceptionHandler();
        }
    }

    /**
     * Nested configuration for Jakarta Persistence (JPA) exceptions ({@link JpaExceptionHandler}).
     * <p>
     * Active only when {@code jakarta.persistence.EntityNotFoundException} is available on the classpath.
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(name = "jakarta.persistence.EntityNotFoundException")
    @ConditionalOnProperty(prefix = "core-helper.exception-handler.jpa", name = "enabled", havingValue = "true", matchIfMissing = true)
    public static class JpaExceptionConfiguration {

        @Bean
        @ConditionalOnMissingBean(JpaExceptionHandler.class)
        public JpaExceptionHandler jpaExceptionHandler() {
            return new JpaExceptionHandler();
        }
    }
}
