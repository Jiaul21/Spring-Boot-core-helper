package com.jiaul.corehelper.utility;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RandomUtilsTest {

    @Test
    @DisplayName("UUID generation and validation")
    void testUuid() {
        String uuid = RandomUtils.randomUuid();
        assertThat(uuid).isNotNull().hasSize(36);
        assertThat(RandomUtils.isValidUuid(uuid)).isTrue();

        String noDashes = RandomUtils.randomUuidWithoutDashes();
        assertThat(noDashes).isNotNull().hasSize(32);
        assertThat(noDashes).matches("^[0-9a-fA-F]{32}$");

        // Validation tests
        assertThat(RandomUtils.isValidUuid(UUID.randomUUID().toString())).isTrue();
        assertThat(RandomUtils.isValidUuid("not-a-valid-uuid")).isFalse();
        assertThat(RandomUtils.isValidUuid("")).isFalse();
        assertThat(RandomUtils.isValidUuid("   ")).isFalse();
        assertThat(RandomUtils.isValidUuid(null)).isFalse();
        // Malformed UUID with right length
        assertThat(RandomUtils.isValidUuid("00000000-0000-0000-0000-00000000000Z")).isFalse();
    }

    @Test
    @DisplayName("randomNumeric generation")
    void testRandomNumeric() {
        String num = RandomUtils.randomNumeric(10);
        assertThat(num).hasSize(10).matches("^[0-9]+$");

        assertThatThrownBy(() -> RandomUtils.randomNumeric(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Length must be greater than zero");

        assertThatThrownBy(() -> RandomUtils.randomNumeric(-5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("randomAlphanumeric generation")
    void testRandomAlphanumeric() {
        String alphanumeric = RandomUtils.randomAlphanumeric(16);
        assertThat(alphanumeric).hasSize(16).matches("^[0-9A-Za-z]+$");

        assertThatThrownBy(() -> RandomUtils.randomAlphanumeric(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Length must be greater than zero");
    }

    @Test
    @DisplayName("randomString with custom character pool")
    void testRandomString() {
        String custom = RandomUtils.randomString(8, "ABC");
        assertThat(custom).hasSize(8).matches("^[ABC]+$");

        assertThatThrownBy(() -> RandomUtils.randomString(0, "ABC"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RandomUtils.randomString(5, ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Characters must not be blank");

        assertThatThrownBy(() -> RandomUtils.randomString(5, "   "))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> RandomUtils.randomString(5, null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Random integer and long generation")
    void testRandomNumbers() {
        int boundedInt = RandomUtils.randomInt(10);
        assertThat(boundedInt).isBetween(0, 9);

        assertThatThrownBy(() -> RandomUtils.randomInt(0))
                .isInstanceOf(IllegalArgumentException.class);

        int rangedInt = RandomUtils.randomInt(10, 20);
        assertThat(rangedInt).isBetween(10, 19);

        assertThatThrownBy(() -> RandomUtils.randomInt(20, 10))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> RandomUtils.randomInt(10, 10))
                .isInstanceOf(IllegalArgumentException.class);

        long rangedLong = RandomUtils.randomLong(100L, 200L);
        assertThat(rangedLong).isBetween(100L, 199L);

        assertThatThrownBy(() -> RandomUtils.randomLong(200L, 100L))
                .isInstanceOf(IllegalArgumentException.class);

        double randDouble = RandomUtils.randomDouble();
        assertThat(randDouble).isGreaterThanOrEqualTo(0.0).isLessThan(1.0);

        // Verify randomInt and randomLong unbounded don't throw
        int anyInt = RandomUtils.randomInt();
        long anyLong = RandomUtils.randomLong();
        boolean anyBool = RandomUtils.randomBoolean();
        assertThat(anyBool).isIn(true, false);
    }
}
