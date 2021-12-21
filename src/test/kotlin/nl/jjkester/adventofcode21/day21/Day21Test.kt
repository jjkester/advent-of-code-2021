package nl.jjkester.adventofcode21.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day21Test {
    val startingPositions = 4 to 8

    @Test
    fun playDeterministicGame() {
        assertThat(Day21.playDeterministicGame(startingPositions))
            .isEqualTo(739785)
    }

    @Test
    fun playQuantumGame() {
        assertThat(Day21.playQuantumGame(startingPositions))
            .isEqualTo(444356092776315L)
    }
}
