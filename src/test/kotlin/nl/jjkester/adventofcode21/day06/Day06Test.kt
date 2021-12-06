package nl.jjkester.adventofcode21.day06

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day06Test {
    val initial: List<UInt> = listOf(3u, 4u, 3u, 1u, 2u)

    @Test
    fun countAfter18Days() {
        assertThat(Day06.countAfterDays(initial, 18))
            .isEqualTo(26)
    }

    @Test
    fun countAfter80Days() {
        assertThat(Day06.countAfterDays(initial, 80))
            .isEqualTo(5934)
    }

    @Test
    fun countAfter256Days() {
        assertThat(Day06.countAfterDays(initial, 256))
            .isEqualTo(26984457539)
    }
}
