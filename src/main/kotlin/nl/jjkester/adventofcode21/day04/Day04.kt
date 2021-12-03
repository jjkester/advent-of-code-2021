package nl.jjkester.adventofcode21.day04

import nl.jjkester.adventofcode21.boilerplate.*

object Day04 : D {
    override val day = day(4) {
        val input = input { "bingo.txt" }.sections()
        val draws = input.first().commaSeparated().ints()
        val boards = input.subList(1, input.size)
            .map { boardInput ->
                boardInput.notEmptyLines()
                    .map { row ->
                        row.trim().split(Regex(" +"))
                            .map { it.toIntOrNull() }
                            .requireNoNulls()
                    }
            }

        part {
            answer("Final score of winning board") {
                scoreOfWinningBoard(draws, boards)
            }
        }
        part {
            answer("Final score of losing board") {
                scoreOfLosingBoard(draws, boards)
            }
        }
    }

    fun scoreOfWinningBoard(draws: List<Int>, boards: List<List<List<Int>>>): Int {
        val firstWin = Bingo(draws, boards.bingoCards()).play()
            .groupBy { it.turn }
            .minByOrNull { it.key }
            ?.value

        return requireNotNull(firstWin).maxOf { it.score }
    }

    fun scoreOfLosingBoard(draws: List<Int>, boards: List<List<List<Int>>>): Int {
        val lastWin = Bingo(draws, boards.bingoCards()).play()
            .groupBy { it.turn }
            .maxByOrNull { it.key }
            ?.value

        return requireNotNull(lastWin).minOf { it.score }
    }

    private fun List<List<List<Int>>>.bingoCards() =
        map { rows -> BingoCard(rows.map { row -> row.map { BingoCardPosition(it) } }) }

    @JvmStatic
    fun main(args: Array<String>) {
        main()
    }
}
