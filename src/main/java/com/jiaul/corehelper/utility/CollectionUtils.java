package com.jiaul.corehelper.utility;

import java.util.*;

/**
 * Utility methods for collection, map, and array operations, emptiness checks,
 * size calculation, and null-safe iteration helpers.
 * <p>
 * This class is stateless and cannot be instantiated.
 */
public final class CollectionUtils {

    private CollectionUtils() {
        // Prevent instantiation
    }

    // -----------------------------------------------------------------
    // Collection Emptiness Checks
    // -----------------------------------------------------------------

    /**
     * Checks if a collection is null or contains no elements.
     *
     * @param collection the collection to check, may be {@code null}
     * @return {@code true} if the collection is null or empty, {@code false} otherwise
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Checks if a collection is not empty (not null and contains at least one element).
     *
     * @param collection the collection to check, may be {@code null}
     * @return {@code true} if the collection contains elements, {@code false} otherwise
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    // -----------------------------------------------------------------
    // Map Emptiness Checks
    // -----------------------------------------------------------------

    /**
     * Checks if a map is null or contains no key-value mappings.
     *
     * @param map the map to check, may be {@code null}
     * @return {@code true} if the map is null or empty, {@code false} otherwise
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * Checks if a map is not empty (not null and contains at least one key-value mapping).
     *
     * @param map the map to check, may be {@code null}
     * @return {@code true} if the map contains mappings, {@code false} otherwise
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    // -----------------------------------------------------------------
    // Array Emptiness Checks
    // -----------------------------------------------------------------

    /**
     * Checks if an array is null or has length 0.
     *
     * @param array the array to check, may be {@code null}
     * @return {@code true} if the array is null or has length 0, {@code false} otherwise
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Checks if an array is not empty (not null and has length &gt; 0).
     *
     * @param array the array to check, may be {@code null}
     * @return {@code true} if the array is non-null and contains elements
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    // -----------------------------------------------------------------
    // Null-Safe Collections (Eliminates NullPointerExceptions in loops)
    // -----------------------------------------------------------------

    /**
     * Returns the given list if non-null; otherwise returns an unmodifiable empty list.
     *
     * <pre>
     * for (Item item : CollectionUtils.emptyIfNull(order.getItems())) {
     *     // Never throws NullPointerException even if getItems() is null
     * }
     * </pre>
     *
     * @param <T>  the element type
     * @param list the list to check, may be {@code null}
     * @return non-null {@link List}
     */
    public static <T> List<T> emptyIfNull(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    /**
     * Returns the given set if non-null; otherwise returns an unmodifiable empty set.
     *
     * @param <T> the element type
     * @param set the set to check, may be {@code null}
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
     * @param map the map to check, may be {@code null}
     * @return non-null {@link Map}
     */
    public static <K, V> Map<K, V> emptyIfNull(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    /**
     * Returns the given collection if non-null; otherwise returns an unmodifiable empty collection.
     *
     * @param <T>        the element type
     * @param collection the collection to check, may be {@code null}
     * @return non-null {@link Collection}
     */
    public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
        return collection == null ? Collections.emptyList() : collection;
    }

    // -----------------------------------------------------------------
    // Safe Default Fallbacks
    // -----------------------------------------------------------------

    /**
     * Returns the given value if non-null; otherwise returns the default value.
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
     * Returns the given collection if it is not empty; otherwise returns the default collection.
     *
     * @param <C>               the collection type
     * @param collection        the collection to inspect, may be {@code null}
     * @param defaultCollection the fallback collection
     * @return {@code collection} if not empty, else {@code defaultCollection}
     */
    public static <C extends Collection<?>> C defaultIfEmpty(C collection, C defaultCollection) {
        return isNotEmpty(collection) ? collection : defaultCollection;
    }

    /**
     * Returns the given map if it is not empty; otherwise returns the default map.
     *
     * @param <M>        the map type
     * @param map        the map to inspect, may be {@code null}
     * @param defaultMap the fallback map
     * @return {@code map} if not empty, else {@code defaultMap}
     */
    public static <M extends Map<?, ?>> M defaultIfEmpty(M map, M defaultMap) {
        return isNotEmpty(map) ? map : defaultMap;
    }
}
