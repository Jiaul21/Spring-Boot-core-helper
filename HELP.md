# Developer & Contributor Guide (`core-helper`)

This guide provides instructions and architectural principles for working on, maintaining, and contributing to the `core-helper` library.

---

## 🏛️ Architecture & Principles

`core-helper` is designed as a **foundational, zero-pollution Spring Boot library**:

1. **Zero Classpath Pollution**:
   - `spring-web` and `jackson-annotations` are scoped as `<scope>provided</scope>` and `<optional>true</optional>`.
   - Consumer applications retain 100% control over their Spring Framework and Jackson versions without version collisions.

2. **Standard Thin JAR**:
   - Never use `spring-boot-maven-plugin` with `repackage` goal here. Libraries must produce standard thin library JARs, alongside `-sources.jar` and `-javadoc.jar`.

3. **Java Baseline**:
   - Compiles with `<release>17</release>` for Java 17 LTS baseline compatibility, while running smoothly on Java 21, 25, and higher.

4. **Modular Response Builders**:
   - Inherited hierarchy: `ResponseBuilder` ➔ `SuccessResponseBuilder` ➔ `ClientErrorResponseBuilder` ➔ `ServerErrorResponseBuilder` ➔ `ApiResponseBuilder`.
   - Consumers only need to import `com.jiaul.corehelper.response.ApiResponseBuilder`.

5. **Uniform Exception Handling**:
   - All custom exceptions inherit from `ApiException` which encapsulates HTTP status, `message`, `error`, and `data`.

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
   git tag v${version}      # e.g., git tag v0.0.1
   git push origin v${version}
   ```
3. **Verify on JitPack**:
   - Visit [https://jitpack.io/#Jiaul21/Spring-Boot-core-helper](https://jitpack.io/#Jiaul21/Spring-Boot-core-helper).
   - Click **Get it** on tag `v${version}`. JitPack automatically runs Maven using Java 17 and serves the JAR.

---

### 2. GitHub Packages (Automated CI/CD Workflow)

The repository includes a GitHub Actions workflow (`.github/workflows/deploy.yml`):

1. **Tagging a Release**:
   ```bash
   git tag v${version}      # e.g., git tag v0.0.1
   git push origin v${version}
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
