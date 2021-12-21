package nl.jjkester.adventofcode21.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GameTest {
    @Test
    fun deterministic() {
        assertThat(Game(4, 8, DeterministicDice()).play())
            .isEqualTo(GameResult(1000, 745, 993))
    }
}
