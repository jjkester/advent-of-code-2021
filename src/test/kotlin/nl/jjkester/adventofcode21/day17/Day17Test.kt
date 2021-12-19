package nl.jjkester.adventofcode21.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day17Test {
    val targetArea = (20 to -5) to (30 to -10)

    @Test
    fun highestTrajectory() {
        assertThat(Day17.highestTrajectory(targetArea).first)
            .isEqualTo(6 to 9)
    }

    @Test
    fun numberOfValidVelocities() {
        assertThat(Day17.numberOfValidVelocities(targetArea))
            .isEqualTo(112)
    }
}
