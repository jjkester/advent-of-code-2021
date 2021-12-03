package nl.jjkester.adventofcode21.day04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day04Test {
    val draws = listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
    val boards = listOf(
        listOf(
            listOf(22, 13, 17, 11, 0),
            listOf(8, 2, 23, 4, 24),
            listOf(21, 9, 14, 16, 7),
            listOf(6, 10, 3, 18, 5),
            listOf(1, 12, 20, 15, 19)
        ),
        listOf(
            listOf(3, 15, 0, 2, 22),
            listOf(9, 18, 13, 17, 5),
            listOf(19, 8, 7, 25, 23),
            listOf(20, 11, 10, 24, 4),
            listOf(14, 21, 16, 12, 6)
        ),
        listOf(
            listOf(14, 21, 17, 24, 4),
            listOf(10, 16, 15, 9, 19),
            listOf(18, 8, 23, 26, 20),
            listOf(22, 11, 13, 6, 5),
            listOf(2, 0, 12, 3, 7)
        )
    )

    @Test
    fun scoreOfWinningBoard() {
        assertThat(Day04.scoreOfWinningBoard(draws, boards))
            .isEqualTo(4512)
    }

    @Test
    fun scoreOfLosingBoard() {
        assertThat(Day04.scoreOfLosingBoard(draws, boards))
            .isEqualTo(1924)
    }
}
