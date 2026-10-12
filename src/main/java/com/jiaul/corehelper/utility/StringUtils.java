package com.jiaul.corehelper.utility;

/**
 * Utility methods for string, {@link StringBuilder}, and {@link StringBuffer} manipulation,
 * inspection, and null-safety.
 * <p>
 * Methods accept {@link CharSequence} wherever applicable, providing unified support across
 * {@link String}, {@link StringBuilder}, and {@link StringBuffer} without redundant object allocations.
 * <p>
 * This class is stateless and cannot be instantiated.
 */
public final class StringUtils {

    private StringUtils() {
        // Prevent instantiation
    }

    // -----------------------------------------------------------------
    // Emptiness & Blankness Checks (CharSequence / StringBuilder / StringBuffer)
    // -----------------------------------------------------------------

    /**
     * Checks if a character sequence is blank (null, empty, or contains only whitespace characters).
     * <p>
     * Supports {@link String}, {@link StringBuilder}, and {@link StringBuffer}.
     *
     * <pre>
     * StringUtils.isBlank(null)                  = true
     * StringUtils.isBlank("")                    = true
     * StringUtils.isBlank("   ")                 = true
     * StringUtils.isBlank("hello")               = false
     * StringUtils.isBlank(new StringBuilder("")) = true
     * </pre>
     *
     * @param cs the character sequence to check, may be {@code null}
     * @return {@code true} if the character sequence is null, empty, or contains only whitespace
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs == null) {
            return true;
        }
        int length = cs.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a character sequence is not blank (not null, not empty, and contains at least one non-whitespace character).
     * <p>
     * Supports {@link String}, {@link StringBuilder}, and {@link StringBuffer}.
     *
     * <pre>
     * StringUtils.isNotBlank(null)                  = false
     * StringUtils.isNotBlank("")                    = false
     * StringUtils.isNotBlank("   ")                 = false
     * StringUtils.isNotBlank("hello")               = true
     * StringUtils.isNotBlank(new StringBuilder("a"))= true
     * </pre>
     *
     * @param cs the character sequence to check, may be {@code null}
     * @return {@code true} if the character sequence contains non-whitespace text, {@code false} otherwise
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * Checks if a character sequence is empty (null or length == 0).
     * <p>
     * Supports {@link String}, {@link StringBuilder}, and {@link StringBuffer}.
     *
     * <pre>
     * StringUtils.isEmpty(null)                  = true
     * StringUtils.isEmpty("")                    = true
     * StringUtils.isEmpty("   ")                 = false
     * StringUtils.isEmpty("text")                = false
     * StringUtils.isEmpty(new StringBuilder("")) = true
     * </pre>
     *
     * @param cs the character sequence to check, may be {@code null}
     * @return {@code true} if the character sequence is null or has length 0
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.isEmpty();
    }

    /**
     * Checks if a character sequence is not empty (not null and length &gt; 0).
     * <p>
     * Supports {@link String}, {@link StringBuilder}, and {@link StringBuffer}.
     *
     * <pre>
     * StringUtils.isNotEmpty(null)                  = false
     * StringUtils.isNotEmpty("")                    = false
     * StringUtils.isNotEmpty("   ")                 = true
     * StringUtils.isNotEmpty("text")                = true
     * StringUtils.isNotEmpty(new StringBuilder("x"))= true
     * </pre>
     *
     * @param cs the character sequence to check, may be {@code null}
     * @return {@code true} if the character sequence is not null and has length &gt; 0
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * Checks if a character sequence contains any whitespace characters.
     *
     * @param cs the character sequence to check, may be {@code null}
     * @return {@code true} if any whitespace character is found, {@code false} otherwise
     */
    public static boolean containsWhitespace(CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        int length = cs.length();
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(cs.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    // -----------------------------------------------------------------
    // Safe Default Fallbacks
    // -----------------------------------------------------------------

    /**
     * Returns the given character sequence if it is not blank; otherwise returns the fallback default.
     *
     * <pre>
     * StringUtils.defaultIfBlank("active", "default") = "active"
     * StringUtils.defaultIfBlank("   ", "default")    = "default"
     * StringUtils.defaultIfBlank(null, "default")     = "default"
     * </pre>
     *
     * @param <T>        the character sequence type
     * @param cs         the character sequence to inspect, may be {@code null}
     * @param defaultCs  the fallback default
     * @return {@code cs} if not blank, else {@code defaultCs}
     */
    public static <T extends CharSequence> T defaultIfBlank(T cs, T defaultCs) {
        return isNotBlank(cs) ? cs : defaultCs;
    }

    /**
     * Returns the given character sequence if it is not empty; otherwise returns the fallback default.
     *
     * <pre>
     * StringUtils.defaultIfEmpty("active", "default") = "active"
     * StringUtils.defaultIfEmpty("", "default")       = "default"
     * StringUtils.defaultIfEmpty(null, "default")     = "default"
     * </pre>
     *
     * @param <T>       the character sequence type
     * @param cs        the character sequence to inspect, may be {@code null}
     * @param defaultCs the fallback default
     * @return {@code cs} if not empty, else {@code defaultCs}
     */
    public static <T extends CharSequence> T defaultIfEmpty(T cs, T defaultCs) {
        return isNotEmpty(cs) ? cs : defaultCs;
    }

    /**
     * Returns an empty string {@code ""} if the given sequence is {@code null}; otherwise returns its string representation.
     *
     * @param cs the character sequence to inspect, may be {@code null}
     * @return non-null string
     */
    public static String emptyIfNull(CharSequence cs) {
        return cs == null ? "" : cs.toString();
    }

    /**
     * Returns the string representation of the character sequence, or the given default if {@code null}.
     *
     * @param cs         the character sequence to inspect, may be {@code null}
     * @param defaultStr the fallback default string
     * @return string value or default
     */
    public static String defaultIfNull(CharSequence cs, String defaultStr) {
        return cs != null ? cs.toString() : defaultStr;
    }
}
