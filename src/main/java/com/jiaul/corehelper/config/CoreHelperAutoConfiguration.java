package com.jiaul.corehelper.config;

import com.jiaul.corehelper.exception.CoreExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot Auto-Configuration for {@code core-helper}.
 * <p>
 * Automatically registers {@link CoreExceptionHandler} into the Spring application context
 * when running inside a Servlet-based web application.
 * <p>
 * Can be explicitly disabled via configuration property:
 * <pre>{@code
 * core-helper.exception-handler.enabled=false
 * }</pre>
 */
@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnProperty(prefix = "core-helper.exception-handler", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CoreHelperAutoConfiguration {

    /**
     * Registers a global {@link CoreExceptionHandler} bean if none is already provided.
     *
     * @return the default {@link CoreExceptionHandler}
     */
    @Bean
    @ConditionalOnMissingBean(CoreExceptionHandler.class)
    public CoreExceptionHandler coreExceptionHandler() {
        return new CoreExceptionHandler();
    }
}
