# Developer & Contributor Guide (`core-helper`)

This guide provides instructions and architectural principles for working on, maintaining, and contributing to the `core-helper` library.

---

## 🏛️ Architecture & Principles

`core-helper` is designed as a **foundational, zero-pollution Spring Boot library**:

1. **Zero Classpath Pollution**:
   - `spring-web`, `spring-webmvc`, `jakarta.validation-api`, `jakarta.servlet-api`, `spring-tx`, `jakarta.persistence-api`, `jackson-annotations`, and `slf4j-api` are scoped as `<scope>provided</scope>` and `<optional>true</optional>`.
   - Consumer applications retain 100% control over their dependencies and versions without transitive dependency bloat.

2. **Standard Thin JAR**:
   - Never use `spring-boot-maven-plugin` with `repackage` goal here. Libraries must produce standard thin library JARs, alongside `-sources.jar` and `-javadoc.jar`.

3. **Java Baseline & Broad Spring Compatibility**:
   - Compiles with `<release>17</release>` for Java 17 LTS baseline compatibility, while running smoothly on Java 21, 25, and higher.
   - Compatible across Spring Boot 3.0 through Spring Boot 4.x.

4. **Modular Response Builders**:
   - Inherited hierarchy: `ResponseBuilder` ➔ `SuccessResponseBuilder` ➔ `ClientErrorResponseBuilder` ➔ `ServerErrorResponseBuilder` ➔ `ApiResponseBuilder`.
   - Consumers only need to import `com.jiaul.corehelper.response.ApiResponseBuilder`.

5. **Uniform Exception Handling**:
   - All custom exceptions inherit from `ApiException` which encapsulates HTTP status, `message`, `error`, and `data`.

6. **Modular & Conditional Auto-Configuration**:
   - `CoreHelperAutoConfiguration` registers exception handlers from `com.jiaul.corehelper.handler` conditionally by library / version boundary, using static `ExceptionLogger.logException(...)` for uniform diagnostic logging:
     - `CoreExceptionHandler`: Always active for Servlet web applications (`ApiException` custom exceptions).
     - `SpringWebExceptionHandler`: Conditionally loaded via `@ConditionalOnClass` for `spring-web` (`MethodArgumentNotValidException`, `HttpMessageNotReadableException`).
     - `SpringWebValidationExceptionHandler`: Conditionally loaded via `@ConditionalOnClass(name = "org.springframework.web.method.annotation.HandlerMethodValidationException")` for Spring Web 6.1+ / Spring Boot 3.2+ method validation.
     - `SpringWebMvcExceptionHandler`: Conditionally loaded via `@ConditionalOnClass(name = "org.springframework.web.servlet.resource.NoResourceFoundException")` for Spring Web MVC 6.1+ / Spring Boot 3.2+ unmapped 404 endpoints.
     - `ValidationExceptionHandler`: Conditionally loaded via `@ConditionalOnClass(name = "jakarta.validation.ConstraintViolationException")`.
     - `DataAccessExceptionHandler`: Conditionally loaded via `@ConditionalOnClass(name = "org.springframework.dao.DataIntegrityViolationException")`.
     - `JpaExceptionHandler`: Conditionally loaded via `@ConditionalOnClass(name = "jakarta.persistence.EntityNotFoundException")`.
   - If a consumer does not include an optional dependency, Spring Boot skips the corresponding bean with zero startup reflection errors.
   - Handlers can be disabled globally (`core-helper.exception-handler.enabled=false`) or granularly (`.core.enabled=false`, `.web.enabled=false`, `.web-validation.enabled=false`, `.webmvc.enabled=false`, `.validation.enabled=false`, `.data-access.enabled=false`, `.jpa.enabled=false`).

7. **Modular Utility Classes**:
   - Specialized stateless classes: `StringUtils` (with unified `CharSequence`, `StringBuilder`, and `StringBuffer` support for blank/empty/whitespace checks and safe fallbacks), `CollectionUtils` (collections, maps, arrays, null-safe collections, and default fallbacks), and `RandomUtils` (cryptographically secure random strings, numbers, and UUIDs).

8. **Completely Decoupled Architecture**:
   - `core-helper` has zero mandatory dependencies on persistence or JPA technologies (persistence handlers are purely optional and conditional). It operates independently as a lightweight foundation across any Spring Boot service (REST APIs, microservices, or background jobs).

---

## 🚀 Common Commands

### 1. Build and Run Unit Tests
```bash
mvn clean test
```

### 2. Full Build with Javadoc and Source Packaging
```bash
mvn clean install
```
This command compiles the code, executes all unit tests, generates `-sources.jar` and `-javadoc.jar`, and installs the artifact into your local Maven cache (`~/.m2/repository/com/jiaul/core-helper/`).

### 3. Check for Dependency / Plugin Updates
```bash
mvn versions:display-dependency-updates
```

---

## 📦 Distribution & Publishing

### 1. JitPack (Zero-Configuration Distribution)

**How to Release via JitPack:**
1. **Push your commits**:
   ```bash
   git push origin main
   ```
2. **Tag a Release**:
   ```bash
   git tag v<version>      # e.g., git tag v0.0.1
   git push origin v<version>
   ```
3. **Verify on JitPack**:
   - Visit [https://jitpack.io/#Jiaul21/Spring-Boot-core-helper](https://jitpack.io/#Jiaul21/Spring-Boot-core-helper).
   - Click **Get it** on tag `v<version>`. JitPack automatically runs Maven using Java 17 and serves the JAR.

---

### 2. GitHub Packages (Automated CI/CD Workflow)

The repository includes a GitHub Actions workflow (`.github/workflows/deploy.yml`):

1. **Tagging a Release**:
   ```bash
   git tag v<version>
   git push origin v<version>
   ```
2. **Automated Publishing**:
   - The workflow configures Java 17, sets up the GitHub Packages repository authentication, and executes `mvn deploy`.
   - Published packages will be accessible at `https://maven.pkg.github.com/Jiaul21/Spring-Boot-core-helper`.

---

## 🧪 Testing Guidelines

- **Unit Tests**: Place unit tests in `src/test/java/com/jiaul/corehelper/`.
- **Frameworks**: Use JUnit 5 (`junit-jupiter`) and AssertJ (`assertj-core`).
- **Assertion Style**: Use fluent assertions (`assertThat(response.getStatusCode()).isEqualTo(...)`).
- Maintain 100% test coverage for all public builder methods, exception mappings, and utility functions.
