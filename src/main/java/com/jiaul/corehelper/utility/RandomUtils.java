package com.jiaul.corehelper.utility;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Utility methods for cryptographically secure random numbers, random strings,
 * and UUID generation/validation based on {@link SecureRandom} and {@link UUID}.
 * <p>
 * This class is stateless and cannot be instantiated.
 */
public final class RandomUtils {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final String DIGITS = "0123456789";

    private static final String ALPHANUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    );

    private RandomUtils() {
        // Prevent instantiation
    }

    // -----------------------------------------------------------------
    // UUID Generation & Validation
    // -----------------------------------------------------------------

    /**
     * Generates a new random Type 4 (pseudo-randomly generated) UUID string representation.
     *
     * @return standard 36-character UUID string (e.g. {@code "123e4567-e89b-12d3-a456-426614174000"})
     */
    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * Generates a new random UUID string without hyphens.
     *
     * @return 32-character hexadecimal UUID string
     */
    public static String randomUuidWithoutDashes() {
        return randomUuid().replace("-", "");
    }

    /**
     * Validates whether the given string conforms strictly to the standard UUID format.
     *
     * @param str the string to validate, may be {@code null}
     * @return {@code true} if valid UUID, {@code false} otherwise
     */
    public static boolean isValidUuid(String str) {
        if (StringUtils.isBlank(str) || !UUID_PATTERN.matcher(str).matches()) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // -----------------------------------------------------------------
    // Random Strings
    // -----------------------------------------------------------------

    /**
     * Generates a secure random numeric string of the specified length containing only digits (0-9).
     *
     * @param length the desired length (must be &gt; 0)
     * @return random numeric string
     * @throws IllegalArgumentException if length is &lt;= 0
     */
    public static String randomNumeric(int length) {
        return randomString(length, DIGITS);
    }

    /**
     * Generates a secure random alphanumeric string of the specified length containing digits and letters (0-9, A-Z, a-z).
     *
     * @param length the desired length (must be &gt; 0)
     * @return random alphanumeric string
     * @throws IllegalArgumentException if length is &lt;= 0
     */
    public static String randomAlphanumeric(int length) {
        return randomString(length, ALPHANUMERIC);
    }

    /**
     * Generates a secure random string of the specified length using the provided character pool.
     *
     * @param length     the desired length (must be &gt; 0)
     * @param characters the characters allowed in the generated string (must not be blank)
     * @return random string
     * @throws IllegalArgumentException if length is &lt;= 0 or characters is blank
     */
    public static String randomString(int length, String characters) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }

        if (StringUtils.isBlank(characters)) {
            throw new IllegalArgumentException("Characters must not be blank");
        }

        StringBuilder result = new StringBuilder(length);
        int poolLength = characters.length();

        for (int i = 0; i < length; i++) {
            int index = SECURE_RANDOM.nextInt(poolLength);
            result.append(characters.charAt(index));
        }

        return result.toString();
    }

    // -----------------------------------------------------------------
    // Random Numbers
    // -----------------------------------------------------------------

    /**
     * Generates a pseudorandom, uniformly distributed {@code int} value across all possible 32-bit values.
     *
     * @return random integer
     */
    public static int randomInt() {
        return SECURE_RANDOM.nextInt();
    }

    /**
     * Generates a pseudorandom, uniformly distributed {@code int} value between 0 (inclusive) and bound (exclusive).
     *
     * @param bound the upper bound (exclusive, must be &gt; 0)
     * @return random integer in {@code [0, bound)}
     * @throws IllegalArgumentException if bound is &lt;= 0
     */
    public static int randomInt(int bound) {
        if (bound <= 0) {
            throw new IllegalArgumentException("Bound must be greater than zero");
        }
        return SECURE_RANDOM.nextInt(bound);
    }

    /**
     * Generates a pseudorandom, uniformly distributed {@code int} value between min (inclusive) and max (exclusive).
     *
     * @param min the lower bound (inclusive)
     * @param max the upper bound (exclusive, must be &gt; min)
     * @return random integer in {@code [min, max)}
     * @throws IllegalArgumentException if min &gt;= max
     */
    public static int randomInt(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("Min must be less than max");
        }
        return SECURE_RANDOM.nextInt(min, max);
    }

    /**
     * Generates a pseudorandom, uniformly distributed {@code long} value across all possible 64-bit values.
     *
     * @return random long
     */
    public static long randomLong() {
        return SECURE_RANDOM.nextLong();
    }

    /**
     * Generates a pseudorandom, uniformly distributed {@code long} value between min (inclusive) and max (exclusive).
     *
     * @param min the lower bound (inclusive)
     * @param max the upper bound (exclusive, must be &gt; min)
     * @return random long in {@code [min, max)}
     * @throws IllegalArgumentException if min &gt;= max
     */
    public static long randomLong(long min, long max) {
        if (min >= max) {
            throw new IllegalArgumentException("Min must be less than max");
        }
        return SECURE_RANDOM.nextLong(min, max);
    }

    /**
     * Generates a pseudorandom, uniformly distributed {@code double} value between 0.0 (inclusive) and 1.0 (exclusive).
     *
     * @return random double in {@code [0.0, 1.0)}
     */
    public static double randomDouble() {
        return SECURE_RANDOM.nextDouble();
    }

    /**
     * Generates a pseudorandom, uniformly distributed {@code boolean} value.
     *
     * @return random boolean
     */
    public static boolean randomBoolean() {
        return SECURE_RANDOM.nextBoolean();
    }
}
