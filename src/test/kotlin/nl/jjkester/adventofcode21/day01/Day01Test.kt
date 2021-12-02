package nl.jjkester.adventofcode21.day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day01Test {

    private val values = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Test
    fun individualIncreasing() {
        assertThat(Day01.individualIncreasing(values))
            .isEqualTo(7)
    }

    @Test
    fun windowOfThreeIncreasing() {
        assertThat(Day01.windowOfThreeIncreasing(values))
            .isEqualTo(5)
    }
}
