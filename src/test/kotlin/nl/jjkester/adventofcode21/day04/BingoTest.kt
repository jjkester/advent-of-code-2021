package nl.jjkester.adventofcode21.day04

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BingoTest {

    @Test
    fun testBingoCardCompletedRow() {
        val numbers = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val card = BingoCard(numbers.map { row -> row.map { BingoCardPosition(it) } })

        card.registerDraw(1)
        card.registerDraw(2)
        card.registerDraw(3)

        assertThat(card.completedRowOrColumn).isTrue
        assertThat(card.score).isEqualTo(4 + 5 + 6 + 7 + 8 + 9)
    }

    @Test
    fun testBingoCardCompletedColumn() {
        val numbers = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val card = BingoCard(numbers.map { row -> row.map { BingoCardPosition(it) } })

        card.registerDraw(1)
        card.registerDraw(4)
        card.registerDraw(7)

        assertThat(card.completedRowOrColumn).isTrue
        assertThat(card.score).isEqualTo(2 + 3 + 5 + 6 + 8 + 9)
    }

    @Test
    fun testBingoWinner() {
        val numbers1 = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9)
        )
        val numbers2 = listOf(
            listOf(4, 2, 6),
            listOf(9, 8, 5),
            listOf(1, 7, 3)
        )
        val numbers3 = listOf(
            listOf(7, 8, 9),
            listOf(1, 2, 3),
            listOf(4, 5, 6)
        )

        val card1 = BingoCard(numbers1.map { row -> row.map { BingoCardPosition(it) } })
        val card2 = BingoCard(numbers2.map { row -> row.map { BingoCardPosition(it) } })
        val card3 = BingoCard(numbers3.map { row -> row.map { BingoCardPosition(it) } })

        val game = Bingo(listOf(1, 2, 9, 4, 8, 7, 6, 3, 5), listOf(card1, card2, card3))

        assertThat(game.play()).containsExactlyInAnyOrder(
            BingoResult(card2, 4, 4),
            BingoResult(card1, 6, 7),
            BingoResult(card3, 6, 7)
        )
    }
}
