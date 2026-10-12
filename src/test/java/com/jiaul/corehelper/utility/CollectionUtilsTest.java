package com.jiaul.corehelper.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CollectionUtilsTest {

    @Test
    @DisplayName("Collection emptiness checks")
    void testCollectionEmptiness() {
        assertThat(CollectionUtils.isEmpty((Collection<?>) null)).isTrue();
        assertThat(CollectionUtils.isEmpty(Collections.emptyList())).isTrue();
        assertThat(CollectionUtils.isEmpty(List.of("one"))).isFalse();

        assertThat(CollectionUtils.isNotEmpty(List.of("one"))).isTrue();
        assertThat(CollectionUtils.isNotEmpty(Collections.emptySet())).isFalse();
        assertThat(CollectionUtils.isNotEmpty((Collection<?>) null)).isFalse();
    }

    @Test
    @DisplayName("Map emptiness checks")
    void testMapEmptiness() {
        assertThat(CollectionUtils.isEmpty((Map<?, ?>) null)).isTrue();
        assertThat(CollectionUtils.isEmpty(Collections.emptyMap())).isTrue();
        assertThat(CollectionUtils.isEmpty(Map.of("k", "v"))).isFalse();

        assertThat(CollectionUtils.isNotEmpty(Map.of("k", "v"))).isTrue();
        assertThat(CollectionUtils.isNotEmpty(Collections.emptyMap())).isFalse();
        assertThat(CollectionUtils.isNotEmpty((Map<?, ?>) null)).isFalse();
    }

    @Test
    @DisplayName("Array emptiness checks")
    void testArrayEmptiness() {
        String[] nullArr = null;
        String[] emptyArr = new String[0];
        String[] populatedArr = new String[]{"item"};

        assertThat(CollectionUtils.isEmpty(nullArr)).isTrue();
        assertThat(CollectionUtils.isEmpty(emptyArr)).isTrue();
        assertThat(CollectionUtils.isEmpty(populatedArr)).isFalse();

        assertThat(CollectionUtils.isNotEmpty(populatedArr)).isTrue();
        assertThat(CollectionUtils.isNotEmpty(emptyArr)).isFalse();
        assertThat(CollectionUtils.isNotEmpty(nullArr)).isFalse();
    }

    @Test
    @DisplayName("emptyIfNull for List, Set, Map, and Collection")
    void testEmptyIfNull() {
        List<String> nullList = null;
        assertThat(CollectionUtils.emptyIfNull(nullList)).isNotNull().isEmpty();
        assertThat(CollectionUtils.emptyIfNull(List.of("a"))).containsExactly("a");

        Set<String> nullSet = null;
        assertThat(CollectionUtils.emptyIfNull(nullSet)).isNotNull().isEmpty();
        assertThat(CollectionUtils.emptyIfNull(Set.of("b"))).containsExactly("b");

        Map<String, Integer> nullMap = null;
        assertThat(CollectionUtils.emptyIfNull(nullMap)).isNotNull().isEmpty();
        assertThat(CollectionUtils.emptyIfNull(Map.of("k", 1))).containsEntry("k", 1);

        Collection<String> nullCol = null;
        assertThat(CollectionUtils.emptyIfNull(nullCol)).isNotNull().isEmpty();
        assertThat(CollectionUtils.emptyIfNull((Collection<String>) List.of("c"))).containsExactly("c");
    }

    @Test
    @DisplayName("defaultIfNull and defaultIfEmpty")
    void testDefaults() {
        assertThat(CollectionUtils.defaultIfNull("val", "def")).isEqualTo("val");
        assertThat(CollectionUtils.defaultIfNull(null, "def")).isEqualTo("def");

        List<String> fallbackList = List.of("default");
        assertThat(CollectionUtils.defaultIfEmpty(List.of("actual"), fallbackList)).containsExactly("actual");
        assertThat(CollectionUtils.defaultIfEmpty(Collections.emptyList(), fallbackList)).containsExactly("default");
        assertThat(CollectionUtils.defaultIfEmpty(null, fallbackList)).containsExactly("default");

        Map<String, String> fallbackMap = Map.of("def", "val");
        assertThat(CollectionUtils.defaultIfEmpty(Map.of("act", "val"), fallbackMap)).containsEntry("act", "val");
        assertThat(CollectionUtils.defaultIfEmpty(Collections.emptyMap(), fallbackMap)).containsEntry("def", "val");
        assertThat(CollectionUtils.defaultIfEmpty(null, fallbackMap)).containsEntry("def", "val");
    }
}
