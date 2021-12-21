package nl.jjkester.adventofcode21.day21

import nl.jjkester.adventofcode21.boilerplate.*

object Day21 : Day {
    override val builder = day(21) {
        val startingPositions by input("positions.txt") {
            lineSeparated()
                .mapNotNull { line ->
                    Regex("^Player (\\d) starting position: (\\d+)$")
                        .matchEntire(line.string())
                        ?.groupValues
                }
                .sortedBy { it[1] }
                .map { it.last().toInt() }
                .toPair()
        }

        part {
            answer("Score of losing player multiplied by number of dice rolls") {
                playDeterministicGame(startingPositions)
            }
        }
    }

    fun playDeterministicGame(startingPositions: Pair<Int, Int>): Int {
        return Game(startingPositions.first, startingPositions.second, DeterministicDice()).play().let {
            it.losingScore * it.diceRolls
        }
    }
}
