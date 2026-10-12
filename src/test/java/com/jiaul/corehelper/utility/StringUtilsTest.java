package com.jiaul.corehelper.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilsTest {

    @Test
    @DisplayName("isBlank and isNotBlank checks on String, StringBuilder, and StringBuffer")
    void testBlankness() {
        assertThat(StringUtils.isBlank(null)).isTrue();
        assertThat(StringUtils.isBlank("")).isTrue();
        assertThat(StringUtils.isBlank("   ")).isTrue();
        assertThat(StringUtils.isBlank("\t\n\r")).isTrue();
        assertThat(StringUtils.isBlank("hello")).isFalse();

        // StringBuilder and StringBuffer support
        assertThat(StringUtils.isBlank(new StringBuilder())).isTrue();
        assertThat(StringUtils.isBlank(new StringBuilder("   "))).isTrue();
        assertThat(StringUtils.isBlank(new StringBuilder("hello"))).isFalse();

        assertThat(StringUtils.isBlank(new StringBuffer())).isTrue();
        assertThat(StringUtils.isBlank(new StringBuffer("   "))).isTrue();
        assertThat(StringUtils.isBlank(new StringBuffer("world"))).isFalse();

        // isNotBlank
        assertThat(StringUtils.isNotBlank("hello")).isTrue();
        assertThat(StringUtils.isNotBlank(new StringBuilder("hello"))).isTrue();
        assertThat(StringUtils.isNotBlank(new StringBuffer("world"))).isTrue();
        assertThat(StringUtils.isNotBlank(null)).isFalse();
        assertThat(StringUtils.isNotBlank("   ")).isFalse();
    }

    @Test
    @DisplayName("isEmpty and isNotEmpty checks on String, StringBuilder, and StringBuffer")
    void testEmptiness() {
        assertThat(StringUtils.isEmpty(null)).isTrue();
        assertThat(StringUtils.isEmpty("")).isTrue();
        assertThat(StringUtils.isEmpty("   ")).isFalse(); // whitespace is not empty
        assertThat(StringUtils.isEmpty("text")).isFalse();

        assertThat(StringUtils.isEmpty(new StringBuilder())).isTrue();
        assertThat(StringUtils.isEmpty(new StringBuilder("text"))).isFalse();

        assertThat(StringUtils.isEmpty(new StringBuffer())).isTrue();
        assertThat(StringUtils.isEmpty(new StringBuffer("text"))).isFalse();

        // isNotEmpty
        assertThat(StringUtils.isNotEmpty("text")).isTrue();
        assertThat(StringUtils.isNotEmpty(new StringBuilder("text"))).isTrue();
        assertThat(StringUtils.isNotEmpty(new StringBuffer("text"))).isTrue();
        assertThat(StringUtils.isNotEmpty("")).isFalse();
        assertThat(StringUtils.isNotEmpty(null)).isFalse();
    }

    @Test
    @DisplayName("containsWhitespace checks")
    void testContainsWhitespace() {
        assertThat(StringUtils.containsWhitespace("hello world")).isTrue();
        assertThat(StringUtils.containsWhitespace("hello\tworld")).isTrue();
        assertThat(StringUtils.containsWhitespace("helloworld")).isFalse();
        assertThat(StringUtils.containsWhitespace("")).isFalse();
        assertThat(StringUtils.containsWhitespace(null)).isFalse();
    }

    @Test
    @DisplayName("defaultIfBlank and defaultIfEmpty fallbacks")
    void testFallbacks() {
        assertThat(StringUtils.defaultIfBlank("active", "default")).isEqualTo("active");
        assertThat(StringUtils.defaultIfBlank("   ", "default")).isEqualTo("default");
        assertThat(StringUtils.defaultIfBlank(null, "default")).isEqualTo("default");

        StringBuilder sb = new StringBuilder("active");
        StringBuilder fallback = new StringBuilder("default");
        assertThat(StringUtils.defaultIfBlank(sb, fallback).toString()).isEqualTo("active");
        assertThat(StringUtils.defaultIfBlank(new StringBuilder("   "), fallback).toString()).isEqualTo("default");

        assertThat(StringUtils.defaultIfEmpty("active", "default")).isEqualTo("active");
        assertThat(StringUtils.defaultIfEmpty("   ", "default")).isEqualTo("   ");
        assertThat(StringUtils.defaultIfEmpty("", "default")).isEqualTo("default");
        assertThat(StringUtils.defaultIfEmpty(null, "default")).isEqualTo("default");

        assertThat(StringUtils.emptyIfNull(null)).isEqualTo("");
        assertThat(StringUtils.emptyIfNull("hello")).isEqualTo("hello");
        assertThat(StringUtils.emptyIfNull(new StringBuilder("hello"))).isEqualTo("hello");

        assertThat(StringUtils.defaultIfNull("val", "def")).isEqualTo("val");
        assertThat(StringUtils.defaultIfNull(null, "def")).isEqualTo("def");
    }
}
