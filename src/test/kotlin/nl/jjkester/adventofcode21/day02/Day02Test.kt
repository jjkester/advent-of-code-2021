package nl.jjkester.adventofcode21.day02

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day02Test {

    private val values = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
        ""
    )
    private val instructions = listOf(
        "forward" to 5,
        "down" to 5,
        "forward" to 8,
        "up" to 3,
        "down" to 8,
        "forward" to 2
    )

    @Test
    fun parseInstructions() {
        assertThat(Day02.parseInstructions(values))
            .isEqualTo(instructions)
    }

    @Test
    fun horizontalAndDepth() {
        assertThat(Day02.horizontalAndDepth(instructions))
            .isEqualTo(150)
    }

    @Test
    fun horizontalAndDepthWithAim() {
        assertThat(Day02.horizontalAndDepthWithAim(instructions))
            .isEqualTo(900)
    }
}
