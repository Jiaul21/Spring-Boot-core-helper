package com.jiaul.corehelper.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UtilityTest {

    @Test
    @DisplayName("String blank and notBlank checks")
    void testStringBlankness() {
        assertThat(Utility.isBlank(null)).isTrue();
        assertThat(Utility.isBlank("")).isTrue();
        assertThat(Utility.isBlank("   ")).isTrue();
        assertThat(Utility.isBlank("hello")).isFalse();

        assertThat(Utility.isNotBlank("hello")).isTrue();
        assertThat(Utility.isNotBlank("  world  ")).isTrue();
        assertThat(Utility.isNotBlank("   ")).isFalse();
        assertThat(Utility.isNotBlank(null)).isFalse();
    }

    @Test
    @DisplayName("String empty and notEmpty checks")
    void testStringEmptiness() {
        assertThat(Utility.isEmpty((String) null)).isTrue();
        assertThat(Utility.isEmpty("")).isTrue();
        assertThat(Utility.isEmpty("   ")).isFalse(); // whitespace is not empty
        assertThat(Utility.isEmpty("text")).isFalse();

        assertThat(Utility.isNotEmpty("text")).isTrue();
        assertThat(Utility.isNotEmpty("   ")).isTrue();
        assertThat(Utility.isNotEmpty("")).isFalse();
        assertThat(Utility.isNotEmpty((String) null)).isFalse();
    }

    @Test
    @DisplayName("Collection empty and notEmpty checks")
    void testCollectionEmptiness() {
        assertThat(Utility.isEmpty((List<?>) null)).isTrue();
        assertThat(Utility.isEmpty(Collections.emptyList())).isTrue();
        assertThat(Utility.isEmpty(List.of("item"))).isFalse();

        assertThat(Utility.isNotEmpty(List.of("item"))).isTrue();
        assertThat(Utility.isNotEmpty(Collections.emptyList())).isFalse();
        assertThat(Utility.isNotEmpty((List<?>) null)).isFalse();
    }

    @Test
    @DisplayName("Map empty and notEmpty checks")
    void testMapEmptiness() {
        assertThat(Utility.isEmpty((Map<?, ?>) null)).isTrue();
        assertThat(Utility.isEmpty(Map.of())).isTrue();
        assertThat(Utility.isEmpty(Map.of("a", 1))).isFalse();

        assertThat(Utility.isNotEmpty(Map.of("a", 1))).isTrue();
        assertThat(Utility.isNotEmpty(Map.of())).isFalse();
        assertThat(Utility.isNotEmpty((Map<?, ?>) null)).isFalse();
    }

    @Test
    @DisplayName("Array empty and notEmpty checks")
    void testArrayEmptiness() {
        String[] nullArray = null;
        String[] emptyArray = new String[0];
        String[] nonEmptyArray = new String[]{"val"};

        assertThat(Utility.isEmpty(nullArray)).isTrue();
        assertThat(Utility.isEmpty(emptyArray)).isTrue();
        assertThat(Utility.isEmpty(nonEmptyArray)).isFalse();

        assertThat(Utility.isNotEmpty(nonEmptyArray)).isTrue();
        assertThat(Utility.isNotEmpty(emptyArray)).isFalse();
        assertThat(Utility.isNotEmpty(nullArray)).isFalse();
    }

    @Test
    @DisplayName("defaultIfNull fallback tests")
    void testDefaultIfNull() {
        assertThat(Utility.defaultIfNull("hello", "fallback")).isEqualTo("hello");
        assertThat(Utility.defaultIfNull(null, "fallback")).isEqualTo("fallback");
        assertThat(Utility.defaultIfNull(null, 100)).isEqualTo(100);
        assertThat(Utility.defaultIfNull(50, 100)).isEqualTo(50);
    }

    @Test
    @DisplayName("defaultIfBlank fallback tests")
    void testDefaultIfBlank() {
        assertThat(Utility.defaultIfBlank("active", "default")).isEqualTo("active");
        assertThat(Utility.defaultIfBlank("   ", "default")).isEqualTo("default");
        assertThat(Utility.defaultIfBlank("", "default")).isEqualTo("default");
        assertThat(Utility.defaultIfBlank(null, "default")).isEqualTo("default");
    }

    @Test
    @DisplayName("defaultIfEmpty fallback tests")
    void testDefaultIfEmpty() {
        assertThat(Utility.defaultIfEmpty("active", "default")).isEqualTo("active");
        assertThat(Utility.defaultIfEmpty("   ", "default")).isEqualTo("   "); // whitespace is not empty
        assertThat(Utility.defaultIfEmpty("", "default")).isEqualTo("default");
        assertThat(Utility.defaultIfEmpty(null, "default")).isEqualTo("default");
    }

    @Test
    @DisplayName("emptyIfNull for List, Set, and Map")
    void testEmptyIfNull() {
        List<String> nullList = null;
        assertThat(Utility.emptyIfNull(nullList)).isNotNull().isEmpty();
        assertThat(Utility.emptyIfNull(List.of("a"))).containsExactly("a");

        Set<String> nullSet = null;
        assertThat(Utility.emptyIfNull(nullSet)).isNotNull().isEmpty();
        assertThat(Utility.emptyIfNull(Set.of("b"))).containsExactly("b");

        Map<String, String> nullMap = null;
        assertThat(Utility.emptyIfNull(nullMap)).isNotNull().isEmpty();
        assertThat(Utility.emptyIfNull(Map.of("k", "v"))).containsEntry("k", "v");
    }

    @Test
    @DisplayName("isValidUuid validation tests")
    void testIsValidUuid() {
        String validUuid = UUID.randomUUID().toString();
        assertThat(Utility.isValidUuid(validUuid)).isTrue();
        assertThat(Utility.isValidUuid("invalid-uuid-string")).isFalse();
        assertThat(Utility.isValidUuid("")).isFalse();
        assertThat(Utility.isValidUuid("   ")).isFalse();
        assertThat(Utility.isValidUuid(null)).isFalse();
    }
}
