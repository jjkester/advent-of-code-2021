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
        part {
            answer("Number of universes where the winning player won") {
                playQuantumGame(startingPositions)
            }
        }
    }

    fun playDeterministicGame(startingPositions: Pair<Int, Int>): Int {
        return Game(startingPositions.first, startingPositions.second, DeterministicDice(), 1000).play().let {
            it.losingScore * it.diceRolls
        }
    }

    fun playQuantumGame(startingPositions: Pair<Int, Int>): Long {
        val game = Game(startingPositions.first, startingPositions.second, DiracDice(), 21)

        game.play()

        val counts = mutableMapOf(Player.One to 0L, Player.Two to 0L)

        game.gameStates.forEach { (state, count) ->
            counts.computeIfPresent(state.maxByOrNull { it.score }!!.player) { _, c -> c + count }
        }

        return counts.maxOf { it.value }
    }
}
