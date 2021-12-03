package nl.jjkester.adventofcode21.day03

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day03Test {
    val values = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )

    @Test
    fun powerConsumption() {
        assertThat(Day03.powerConsumption(values))
            .isEqualTo(198)
    }

    @Test
    fun oxygenGeneratorRating() {
        assertThat(Day03.oxygenGeneratorRating(values))
            .isEqualTo(23)
    }

    @Test
    fun co2ScrubberRating() {
        assertThat(Day03.co2ScrubberRating(values))
            .isEqualTo(10)
    }

    @Test
    fun lifeSupportRating() {
        assertThat(Day03.lifeSupportRating(values))
            .isEqualTo(230)
    }
}
