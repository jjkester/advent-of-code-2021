package nl.jjkester.adventofcode21.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day21Test {

    @Test
    fun playDeterministicGame() {
        assertThat(Day21.playDeterministicGame(4 to 8))
            .isEqualTo(739785)
    }
}
