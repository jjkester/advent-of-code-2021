package nl.jjkester.adventofcode21.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day11Test {
    val minimal = listOf(
        listOf(1, 1, 1, 1, 1),
        listOf(1, 9, 9, 9, 1),
        listOf(1, 9, 1, 9, 1),
        listOf(1, 9, 9, 9, 1),
        listOf(1, 1, 1, 1, 1),
    )

    val initial = listOf(
        listOf(5, 4, 8, 3, 1, 4, 3, 2, 2, 3),
        listOf(2, 7, 4, 5, 8, 5, 4, 7, 1, 1),
        listOf(5, 2, 6, 4, 5, 5, 6, 1, 7, 3),
        listOf(6, 1, 4, 1, 3, 3, 6, 1, 4, 6),
        listOf(6, 3, 5, 7, 3, 8, 5, 4, 7, 8),
        listOf(4, 1, 6, 7, 5, 2, 4, 6, 4, 5),
        listOf(2, 1, 7, 6, 8, 4, 1, 7, 2, 1),
        listOf(6, 8, 8, 2, 8, 8, 1, 1, 3, 4),
        listOf(4, 8, 4, 6, 8, 4, 8, 5, 5, 4),
        listOf(5, 2, 8, 3, 7, 5, 1, 5, 2, 6),
    )

    @Test
    fun totalFlashesIn2Steps() {
        assertThat(Day11.totalFlashesInSteps(minimal, 2))
            .isEqualTo(9)
    }

    @Test
    fun totalFlashesIn100Steps() {
        assertThat(Day11.totalFlashesInSteps(initial, 100))
            .isEqualTo(1656)
    }

    @Test
    fun firstSynchronizedStep() {
        assertThat(Day11.firstSynchronizedStep(initial))
            .isEqualTo(195)
    }
}
