# Core Helper (`core-helper`)

[![Java](https://img.shields.io/badge/Java-17%20%7C%2021%20%7C%2025+-blue.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x%20%2B-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![JitPack](https://jitpack.io/v/Jiaul21/Spring-Boot-core-helper.svg)](https://jitpack.io/#Jiaul21/Spring-Boot-core-helper)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)

A foundational, zero-overhead Spring Boot library providing uniform API response builders, standardized API exception hierarchies, and null/empty utility validators.

Designed as a **professional thin library JAR**:
- **Zero Classpath Pollution**: Framework dependencies (`spring-web`, `jackson-annotations`) are marked `provided` and `optional`. Your consumer application retains 100% control over Spring and Jackson versions.
- **IDE Friendly**: Bundles compiled source JAR (`-sources.jar`) and Javadoc JAR (`-javadoc.jar`) for instant code completion and documentation.
- **Java Baseline**: Built for Java 17 minimum, fully compatible with Java 21, 25, and future LTS releases.

---

## 📦 Installation

### Option 1: Via JitPack (Recommended for Remote Consumption)

[JitPack](https://jitpack.io/#Jiaul21/Spring-Boot-core-helper) builds and serves the library directly from GitHub release tags or the `main` branch.

#### Maven (`pom.xml`)
```xml
<!-- 1. Add JitPack repository -->
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<!-- 2. Add dependency -->
<dependencies>
    <dependency>
        <groupId>com.github.Jiaul21</groupId>
        <artifactId>Spring-Boot-core-helper</artifactId>
        <version>${version}</version> <!-- e.g., v0.0.1 or main-SNAPSHOT -->
    </dependency>
</dependencies>
```

#### Gradle (Groovy - `build.gradle`)
```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.Jiaul21:Spring-Boot-core-helper:${version}' // e.g., '...:v0.0.1' or '...:main-SNAPSHOT'
}
```

#### Gradle (Kotlin DSL - `build.gradle.kts`)
```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.Jiaul21:Spring-Boot-core-helper:${version}") // e.g., "...:v0.0.1" or "...:main-SNAPSHOT"
}
```

---

### Option 2: Local Development Cache (`~/.m2`)

If you clone and build locally on your machine via `mvn clean install`, use the local snapshot coordinates:

#### Maven (`pom.xml`)
```xml
<dependency>
    <groupId>com.jiaul</groupId>
    <artifactId>core-helper</artifactId>
    <version>${version}</version> <!-- e.g., 0.0.1-SNAPSHOT -->
</dependency>
```

#### Gradle (Groovy & Kotlin DSL)
```groovy
repositories {
    mavenCentral()
    mavenLocal() // reads from your local ~/.m2/repository cache
}

dependencies {
    implementation 'com.jiaul:core-helper:${version}' // e.g., '...:0.0.1-SNAPSHOT'
}
```

---

### Option 3: GitHub Packages

Published packages can also be consumed from GitHub Packages:

#### Maven (`pom.xml`)
```xml
<!-- 1. Declare GitHub Packages repository -->
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/Jiaul21/Spring-Boot-core-helper</url>
    </repository>
</repositories>

<!-- 2. Add dependency -->
<dependencies>
    <dependency>
        <groupId>com.jiaul</groupId>
        <artifactId>core-helper</artifactId>
        <version>${version}</version>
    </dependency>
</dependencies>
```

#### Gradle (`build.gradle`)
```groovy
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/Jiaul21/Spring-Boot-core-helper")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation 'com.jiaul:core-helper:${version}'
}
```

> **Note**: GitHub Packages requires authentication (a GitHub Personal Access Token in your `~/.m2/settings.xml` or environment variables) even to download public packages. If you want zero-configuration public access without tokens, use **Option 1 (JitPack)**.

---

## 🚀 Features & Usage

### 1. Unified API Responses (`ApiResponseBuilder` & `Response`)
Provides static methods to build standard Spring `ResponseEntity` objects without boilerplate.

`Response` records contain `message` (String), `error` (Object), `data` (Object), and an ISO UTC `Instant timestamp`:

```json
{
  "message": "Validation Failed",
  "error": "Invalid request fields",
  "data": {
    "email": "Email must be valid"
  },
  "timestamp": "2026-10-07T04:35:00Z"
}
```

#### Modular Builder Hierarchy
The response builder is organized into a modular inheritance hierarchy:
- [ResponseBuilder](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/response/ResponseBuilder.java): Core low-level `build(...)` methods for custom `HttpStatus`, headers, and payloads.
- [SuccessResponseBuilder](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/response/SuccessResponseBuilder.java): 2xx success builders (`ok`, `created`, `accepted`, `noContent`).
- [ClientErrorResponseBuilder](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/response/ClientErrorResponseBuilder.java): 4xx client error builders (`badRequest`, `unauthorized`, `forbidden`, `notFound`, `conflict`, `unprocessableEntity`, `tooManyRequests`).
- [ServerErrorResponseBuilder](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/response/ServerErrorResponseBuilder.java): 5xx server error builders (`internalServerError`).
- [ApiResponseBuilder](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/response/ApiResponseBuilder.java): Final unified utility class inheriting all methods from the hierarchy.

```java
import com.jiaul.corehelper.response.ApiResponseBuilder;
import com.jiaul.corehelper.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return ApiResponseBuilder.ok(user); // 200 OK with raw payload
    }

    @GetMapping("/{id}/wrapped")
    public ResponseEntity<Response> getWrappedUser(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return ApiResponseBuilder.ok("User retrieved", user); // 200 OK with Response(message, data)
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        UserDto created = userService.create(request);
        URI location = URI.create("/api/v1/users/" + created.id());
        return ApiResponseBuilder.created(location, created); // 201 Created with Location header
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ApiResponseBuilder.noContent(); // 204 No Content
    }
}
```

#### Available Response Factory Methods
| Method | HTTP Status | Response Body |
|---|---|---|
| `ok()` | `200 OK` | empty |
| `ok(message, data)` | `200 OK` | `Response(message, null, data, timestamp)` |
| `ok(data)` | `200 OK` | `T data` |
| `ok(headers, data)` | `200 OK` | `T data` with `HttpHeaders` |
| `created()` | `201 CREATED` | empty |
| `created(message, data)` | `201 CREATED` | `Response(message, null, data, timestamp)` |
| `created(data)` | `201 CREATED` | `T data` |
| `created(URI location, data)` | `201 CREATED` | `T data` with `Location` header |
| `created(headers, data)` | `201 CREATED` | `T data` with `HttpHeaders` |
| `accepted()` | `202 ACCEPTED` | empty |
| `accepted(message, data)` | `202 ACCEPTED` | `Response(message, null, data, timestamp)` |
| `accepted(data)` | `202 ACCEPTED` | `T data` |
| `noContent()` | `204 NO CONTENT` | empty |
| `badRequest(error)` | `400 BAD REQUEST` | `Response("Bad Request", error, null, timestamp)` |
| `badRequest(error, data)` | `400 BAD REQUEST` | `Response("Bad Request", error, data, timestamp)` |
| `badRequest(message, error, data)` | `400 BAD REQUEST` | `Response(message, error, data, timestamp)` |
| `unauthorized(error)` | `401 UNAUTHORIZED` | `Response("Unauthorized Request", error, null, timestamp)` |
| `unauthorized(error, data)` | `401 UNAUTHORIZED` | `Response("Unauthorized Request", error, data, timestamp)` |
| `unauthorized(message, error, data)` | `401 UNAUTHORIZED` | `Response(message, error, data, timestamp)` |
| `forbidden(error)` | `403 FORBIDDEN` | `Response("Forbidden Request", error, null, timestamp)` |
| `forbidden(error, data)` | `403 FORBIDDEN` | `Response("Forbidden Request", error, data, timestamp)` |
| `forbidden(message, error, data)` | `403 FORBIDDEN` | `Response(message, error, data, timestamp)` |
| `notFound(error)` | `404 NOT FOUND` | `Response("Resource Not Found", error, null, timestamp)` |
| `notFound(error, data)` | `404 NOT FOUND` | `Response("Resource Not Found", error, data, timestamp)` |
| `notFound(message, error, data)` | `404 NOT FOUND` | `Response(message, error, data, timestamp)` |
| `conflict(error)` | `409 CONFLICT` | `Response("Conflict", error, null, timestamp)` |
| `conflict(error, data)` | `409 CONFLICT` | `Response("Conflict", error, data, timestamp)` |
| `conflict(message, error, data)` | `409 CONFLICT` | `Response(message, error, data, timestamp)` |
| `unprocessableEntity(error)` | `422 UNPROCESSABLE ENTITY` | `Response("Unprocessable Entity", error, null, timestamp)` |
| `unprocessableEntity(error, data)` | `422 UNPROCESSABLE ENTITY` | `Response("Unprocessable Entity", error, data, timestamp)` |
| `unprocessableEntity(message, error, data)` | `422 UNPROCESSABLE ENTITY` | `Response(message, error, data, timestamp)` |
| `tooManyRequests(error)` | `429 TOO MANY REQUESTS` | `Response("Too Many Requests", error, null, timestamp)` |
| `tooManyRequests(error, data)` | `429 TOO MANY REQUESTS` | `Response("Too Many Requests", error, data, timestamp)` |
| `tooManyRequests(message, error, data)` | `429 TOO MANY REQUESTS` | `Response(message, error, data, timestamp)` |
| `internalServerError()` / `internalServerError(error)` | `500 INTERNAL SERVER ERROR` | `Response("Internal Server Error", error, null, timestamp)` |
| `internalServerError(error, data)` | `500 INTERNAL SERVER ERROR` | `Response("Internal Server Error", error, data, timestamp)` |
| `internalServerError(message, error, data)` | `500 INTERNAL SERVER ERROR` | `Response(message, error, data, timestamp)` |
| `build(status, message)` | Custom `HttpStatus` | `Response(message, null, null, timestamp)` |
| `build(status, message, error)` | Custom `HttpStatus` | `Response(message, error, null, timestamp)` |
| `build(status, message, error, data)` | Custom `HttpStatus` | `Response(message, error, data, timestamp)` |
| `build(status, data)` | Custom `HttpStatus` | `T data` |
| `build(status, headers, data)` | Custom `HttpStatus` | `T data` with `HttpHeaders` |

---

### 2. Standardized API Exceptions (`ApiException`)
Clean exception hierarchy carrying `message` (String, sent to `super`), `error` (Object, via `getError()`), `data` (Object, via `getData()`), and HTTP statuses (`getHttpStatus()`):

```java
import com.jiaul.corehelper.exception.*;

// 400 Bad Request (with structured validation data)
Map<String, String> fieldErrors = Map.of("email", "Email is required");
throw new BadRequestException("Validation failed", "Invalid input parameters", fieldErrors);

// 401 Unauthorized
throw new UnauthorizedException("Unauthorized", "JWT token has expired");

// 403 Forbidden
throw new ForbiddenException("Forbidden", "Account lacks required permissions");

// 404 Not Found
throw new NotFoundException("Not Found", "User not found with id: " + id);

// 409 Conflict
throw new ConflictException("Conflict", "Username already taken", Map.of("field", "username"));

// 422 Unprocessable Entity (domain / business rule validation)
throw new UnprocessableEntityException("Unprocessable Entity", "End date must be after start date", "date_range");

// 429 Too Many Requests (rate limiting)
throw new TooManyRequestsException("Too Many Requests", "Rate limit exceeded", "Retry in 60s");
```

#### Global Exception Handler Integration
Because every exception extends `ApiException` and exposes `getHttpStatus()`, `getMessage()`, `getError()`, and `getData()`, consumer applications can handle all API exceptions with a single method:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Response> handleApiException(ApiException ex) {
        return ApiResponseBuilder.build(ex.getHttpStatus(), ex.getMessage(), ex.getError(), ex.getData());
    }
}
```

---

### 3. Null, Defaults & Collection Utilities (`Utility`)
Pure static, stateless methods to prevent `NullPointerException`s, handle safe fallbacks, and validate UUIDs:

```java
import com.jiaul.corehelper.utility.Utility;

// Null & Blank Checks
Utility.isNotBlank("hello");       // true
Utility.isBlank("   ");            // true
Utility.isEmpty(userList);         // true if null or empty

// Safe Defaults (replaces verbose ternary null-checks)
int pageSize = Utility.defaultIfNull(request.getPageSize(), 20);
String sort = Utility.defaultIfBlank(request.getSortBy(), "createdAt");

// Null-Safe Collections (loop safely without NPE)
for (Item item : Utility.emptyIfNull(order.getItems())) {
    process(item);
}

// UUID Validation
if (!Utility.isValidUuid(idParam)) {
    throw new BadRequestException("Invalid UUID format");
}
```

---

## 🛠️ Building From Source

```bash
# Clone the repository
git clone https://github.com/Jiaul21/Spring-Boot-core-helper.git
cd Spring-Boot-core-helper

# Run tests
mvn clean test

# Build and install JARs to local ~/.m2 repository
mvn clean install
```

> **Note**: When consuming artifacts locally in other Maven or Gradle projects on the same machine, running `mvn clean install` places `core-helper-${version}.jar` directly into your local `~/.m2/repository/com/jiaul/core-helper/` cache.
