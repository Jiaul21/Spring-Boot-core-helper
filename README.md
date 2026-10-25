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
    implementation 'com.github.Jiaul21:Spring-Boot-core-helper:<version>' // e.g., '...:v0.0.1' or '...:main-SNAPSHOT'
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

#### Gradle (Groovy)
```groovy
repositories {
    mavenCentral()
    mavenLocal() // reads from your local ~/.m2/repository cache
}

dependencies {
    implementation 'com.jiaul:core-helper:<version>' // e.g., '...:0.0.1-SNAPSHOT'
}
```

#### Gradle (Kotlin DSL - `build.gradle.kts`)
```kotlin
repositories {
    mavenCentral()
    mavenLocal() // reads from your local ~/.m2/repository cache
}

dependencies {
    implementation("com.jiaul:core-helper:<version>") // e.g., "...:0.0.1-SNAPSHOT"
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

#### Gradle (Groovy - `build.gradle`)
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
    implementation 'com.jiaul:core-helper:<version>'
}
```

#### Gradle (Kotlin DSL - `build.gradle.kts`)
```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/Jiaul21/Spring-Boot-core-helper")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation("com.jiaul:core-helper:${version}")
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
| `methodNotAllowed(error)` | `405 METHOD NOT ALLOWED` | `Response("Method Not Allowed", error, null, timestamp)` |
| `methodNotAllowed(error, data)` | `405 METHOD NOT ALLOWED` | `Response("Method Not Allowed", error, data, timestamp)` |
| `methodNotAllowed(message, error, data)` | `405 METHOD NOT ALLOWED` | `Response(message, error, data, timestamp)` |
| `requestTimeout(error)` | `408 REQUEST TIMEOUT` | `Response("Request Timeout", error, null, timestamp)` |
| `requestTimeout(error, data)` | `408 REQUEST TIMEOUT` | `Response("Request Timeout", error, data, timestamp)` |
| `requestTimeout(message, error, data)` | `408 REQUEST TIMEOUT` | `Response(message, error, data, timestamp)` |
| `conflict(error)` | `409 CONFLICT` | `Response("Conflict", error, null, timestamp)` |
| `conflict(error, data)` | `409 CONFLICT` | `Response("Conflict", error, data, timestamp)` |
| `conflict(message, error, data)` | `409 CONFLICT` | `Response(message, error, data, timestamp)` |
| `unsupportedMediaType(error)` | `415 UNSUPPORTED MEDIA TYPE` | `Response("Unsupported Media Type", error, null, timestamp)` |
| `unsupportedMediaType(error, data)` | `415 UNSUPPORTED MEDIA TYPE` | `Response("Unsupported Media Type", error, data, timestamp)` |
| `unsupportedMediaType(message, error, data)` | `415 UNSUPPORTED MEDIA TYPE` | `Response(message, error, data, timestamp)` |
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

// 405 Method Not Allowed
throw new MethodNotAllowedException("Method Not Allowed", "POST not supported", Map.of("allowed", "GET"));

// 408 Request Timeout
throw new RequestTimeoutException("Request Timeout", "Upstream server timed out");

// 409 Conflict
throw new ConflictException("Conflict", "Username already taken", Map.of("field", "username"));

// 415 Unsupported Media Type
throw new UnsupportedMediaTypeException("Unsupported Media Type", "XML format is not supported");

// 422 Unprocessable Entity (domain / business rule validation)
throw new UnprocessableEntityException("Unprocessable Entity", "End date must be after start date", "date_range");

// 429 Too Many Requests (rate limiting)
throw new TooManyRequestsException("Too Many Requests", "Rate limit exceeded", "Retry in 60s");
```

---

### 3. Automatic Global Exception Handling (`CoreExceptionHandler`)

`core-helper` provides an **out-of-the-box global exception handler** registered automatically via Spring Boot Auto-Configuration (`CoreHelperAutoConfiguration`).

#### How It Works:
1. **Zero Configuration**: Any `ApiException` thrown by your controllers or service layer is automatically intercepted.
2. **Unified JSON Response**: It is immediately serialized into the standardized `Response` JSON format with the matching HTTP status code.
3. **Structured Diagnostic Logging**: Logs origin class, method, line number, HTTP status, message, error payload, and data payload to SLF4J:
   ```text
   Origin     : com.example.service.UserService.findById:42
   Exception  : NotFoundException
   HttpStatus : 404 NOT_FOUND
   Message    : Not Found
   Error      : User not found with id: 123
   Data       : N/A
   ```
4. **Non-Intrusive Priority**: Marked with `@Order(Ordered.LOWEST_PRECEDENCE)` so any application-level `@RestControllerAdvice` you write will automatically take precedence over the default handler.

#### Disabling Auto-Configuration (Optional)
If you wish to manage all exception handling manually, you can disable the default handler in `application.properties` or `application.yml`:

```properties
core-helper.exception-handler.enabled=false
```

---

### 4. Modular Utility Classes

`core-helper` provides a high-performance, stateless utility suite divided into specialized classes:

#### A. String & Buffer Utilities ([StringUtils](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/utility/StringUtils.java))
All methods accept `CharSequence`, enabling unified support across `String`, `StringBuilder`, and `StringBuffer` without unnecessary allocations:

```java
import com.jiaul.corehelper.utility.StringUtils;

// Blank & Empty Checks (supports String, StringBuilder, StringBuffer)
StringUtils.isBlank("   ");                      // true
StringUtils.isNotBlank(new StringBuilder("ok")); // true
StringUtils.isEmpty("");                         // true
StringUtils.isNotEmpty(new StringBuffer("hi"));  // true
StringUtils.containsWhitespace("foo bar");       // true

// Safe Defaults & Null Conversions
String val = StringUtils.defaultIfBlank(input, "fallback");
String tag = StringUtils.defaultIfEmpty(emptyStr, "defaultTag");
String nonNull = StringUtils.emptyIfNull(nullableStr); // returns "" if null
String strVal = StringUtils.defaultIfNull(nullCs, "default");
```

#### B. Collection & Map Utilities ([CollectionUtils](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/utility/CollectionUtils.java))
Eliminates `NullPointerException`s in loops and provides safe defaults:

```java
import com.jiaul.corehelper.utility.CollectionUtils;

// Emptiness Checks (Collections, Maps, Arrays)
CollectionUtils.isEmpty(userList);           // true if null or empty
CollectionUtils.isNotEmpty(requestMap);      // true if non-null and size > 0
CollectionUtils.isEmpty(new String[0]);      // true

// Null-Safe Collections (loop safely without ternary null checks)
for (Item item : CollectionUtils.emptyIfNull(order.getItems())) {
    process(item); // never throws NPE even if getItems() is null
}

// Safe Defaults
int pageSize = CollectionUtils.defaultIfNull(request.getPageSize(), 20);
List<String> list = CollectionUtils.defaultIfEmpty(customTags, defaultTags);
Map<String, String> map = CollectionUtils.defaultIfEmpty(customProps, defaultProps);
```

#### C. Secure Random & UUID Utilities ([RandomUtils](file:///d:/enterprise-system/spring-helper/core-helper/src/main/java/com/jiaul/corehelper/utility/RandomUtils.java))
Cryptographically secure random values and RFC-compliant UUID operations backed by `SecureRandom`:

```java
import com.jiaul.corehelper.utility.RandomUtils;

// UUID Generation & Strict Validation
String id = RandomUtils.randomUuid();                 // e.g. "c9a646d3-9c61-4cc9-bc55-e4604e0e4708"
String hexId = RandomUtils.randomUuidWithoutDashes(); // 32 hex chars without hyphens
boolean valid = RandomUtils.isValidUuid(idParam);     // true if valid UUID regex + parse

// Secure Random Strings
String otp = RandomUtils.randomNumeric(6);            // e.g. "948201" (digits only)
String token = RandomUtils.randomAlphanumeric(16);    // e.g. "7kR9m2WqPx81aL0b"
String custom = RandomUtils.randomString(8, "ABCXYZ");// custom character pool

// Secure Random Numbers
int pin = RandomUtils.randomInt(1000, 9999);          // range [min, max)
long sessionVal = RandomUtils.randomLong(100L, 500L);
double factor = RandomUtils.randomDouble();           // in [0.0, 1.0)
boolean coinFlip = RandomUtils.randomBoolean();
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
