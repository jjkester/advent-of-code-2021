package nl.jjkester.adventofcode21.day07

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day07Test {
    val initial = listOf(16, 1, 2, 0, 4, 2, 7, 1, 2, 14)

    @Test
    fun cheapestPositionLinearCost() {
        assertThat(Day07.cheapestPositionLinearCost(initial))
            .isEqualTo(37)
    }

    @Test
    fun cheapestPositionIncreasingCost() {
        assertThat(Day07.cheapestPositionIncreasingCost(initial))
            .isEqualTo(168)
    }
}
