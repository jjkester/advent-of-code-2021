package nl.jjkester.adventofcode21.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day13Test {
    val dots = setOf(
        6 to 10,
        0 to 14,
        9 to 10,
        0 to 3,
        10 to 4,
        4 to 11,
        6 to 0,
        6 to 12,
        4 to 1,
        0 to 13,
        10 to 12,
        3 to 4,
        3 to 0,
        8 to 4,
        1 to 10,
        2 to 14,
        8 to 10,
        9 to 0,
    )

    @Test
    fun foldHorizontal() {
        assertThat(Day13.foldHorizontal(dots, 7))
            .containsExactlyInAnyOrder(
                0 to 0,
                0 to 1,
                0 to 3,
                1 to 4,
                2 to 0,
                3 to 0,
                3 to 4,
                4 to 1,
                4 to 3,
                6 to 0,
                6 to 2,
                6 to 4,
                8 to 4,
                9 to 0,
                9 to 4,
                10 to 2,
                10 to 4
            )
    }
}
