package com.jiaul.corehelper.utility;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * General-purpose static utility methods for null, emptiness, blankness checks,
 * safe default fallbacks, and null-safe collection handling across Strings, Collections,
 * Maps, Arrays, and UUIDs.
 * <p>
 * This class is stateless and cannot be instantiated.
 */
public final class Utility {

    private Utility() {
        // Prevent instantiation
    }

    // -----------------------------------------------------------------
    // String Checks
    // -----------------------------------------------------------------

    /**
     * Checks if a string is not blank (not null, not empty, and contains at least one non-whitespace character).
     *
     * @param string the string to check
     * @return {@code true} if the string contains non-whitespace text, {@code false} otherwise
     */
    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }

    /**
     * Checks if a string is blank (null, empty, or contains only whitespace characters).
     *
     * @param string the string to check
     * @return {@code true} if the string is null, empty, or contains only whitespace
     */
    public static boolean isBlank(String string) {
        return string == null || string.isBlank();
    }

    /**
     * Checks if a string is not empty (not null and length &gt; 0).
     *
     * @param string the string to check
     * @return {@code true} if the string is not null and has at least one character
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * Checks if a string is empty (null or length == 0).
     *
     * @param string the string to check
     * @return {@code true} if the string is null or empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }



    // -----------------------------------------------------------------
    // Collection Checks
    // -----------------------------------------------------------------

    /**
     * Checks if a collection is not empty (not null and contains elements).
     *
     * @param collection the collection to check
     * @return {@code true} if the collection is not null and contains elements
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Checks if a collection is empty (null or contains no elements).
     *
     * @param collection the collection to check
     * @return {@code true} if the collection is null or empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    // -----------------------------------------------------------------
    // Map Checks
    // -----------------------------------------------------------------

    /**
     * Checks if a map is not empty (not null and contains key-value mappings).
     *
     * @param map the map to check
     * @return {@code true} if the map is not null and contains mappings
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Checks if a map is empty (null or contains no key-value mappings).
     *
     * @param map the map to check
     * @return {@code true} if the map is null or empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    // -----------------------------------------------------------------
    // Array Checks
    // -----------------------------------------------------------------

    /**
     * Checks if an array is not empty (not null and length &gt; 0).
     *
     * @param array the array to check
     * @return {@code true} if the array is not null and contains elements
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * Checks if an array is empty (null or length == 0).
     *
     * @param array the array to check
     * @return {@code true} if the array is null or has length 0
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    // -----------------------------------------------------------------
    // Safe Default Fallbacks
    // -----------------------------------------------------------------

    /**
     * Returns the given value if it is not null; otherwise returns the default value.
     *
     * @param <T>          the value type
     * @param value        the value to inspect
     * @param defaultValue the fallback default value
     * @return {@code value} if non-null, else {@code defaultValue}
     */
    public static <T> T defaultIfNull(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * Returns the given string if it is not blank; otherwise returns the default string.
     *
     * @param str        the string to inspect
     * @param defaultStr the fallback default string
     * @return {@code str} if not blank, else {@code defaultStr}
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isNotBlank(str) ? str : defaultStr;
    }

    /**
     * Returns the given string if it is not empty; otherwise returns the default string.
     *
     * @param str        the string to inspect
     * @param defaultStr the fallback default string
     * @return {@code str} if not empty, else {@code defaultStr}
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return isNotEmpty(str) ? str : defaultStr;
    }

    // -----------------------------------------------------------------
    // Null-Safe Collections (Eliminates NullPointerException in loops)
    // -----------------------------------------------------------------

    /**
     * Returns the given list if non-null; otherwise returns an unmodifiable empty list.
     *
     * @param <T>  the element type
     * @param list the list to check
     * @return non-null {@link List}
     */
    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * Returns the given set if non-null; otherwise returns an unmodifiable empty set.
     *
     * @param <T> the element type
     * @param set the set to check
     * @return non-null {@link Set}
     */
    public static <T> Set<T> emptyIfNull(Set<T> set) {
        return set == null ? Collections.emptySet() : set;
    }

    /**
     * Returns the given map if non-null; otherwise returns an unmodifiable empty map.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param map the map to check
     * @return non-null {@link Map}
     */
    public static <K, V> Map<K, V> emptyIfNull(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    // -----------------------------------------------------------------
    // Identifier Validation
    // -----------------------------------------------------------------

    /**
     * Validates whether the given string is a valid standard UUID format.
     *
     * @param str the string to test
     * @return {@code true} if valid UUID, {@code false} otherwise
     */
    public static boolean isValidUuid(String str) {
        if (isBlank(str)) {
            return false;
        }
        try {
            UUID.fromString(str);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
