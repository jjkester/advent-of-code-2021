package nl.jjkester.adventofcode21.day21

class Game(player1: Int, player2: Int, private val dice: Dice) {
    init {
        require(player1 in 1..10) { "Invalid starting position for player 1: $player1" }
        require(player2 in 1..10) { "Invalid starting position for player 2: $player2" }
    }

    private val position = intArrayOf(player1 - 1, player2 - 1)
    private val score = IntArray(2) { 0 }

    fun play(): GameResult {
        var player = 0

        while (!hasWinner()) {
            val rolls = List(3) { dice.roll() }
            move(player, rolls.sum())
//            println("Player ${player + 1} rolls ${rolls.joinToString("+")} and moves to space ${position[player] + 1} for a total score of ${score[player]}")
            player = (player + 1) % 2
        }

        return GameResult(score.maxOf { it }, score.minOf { it }, dice.rolls)
    }

    private fun move(player: Int, positions: Int) {
        position[player] = (position[player] + positions) % 10
        score[player] += position[player] + 1
    }

    private fun hasWinner(): Boolean {
        return score[0] >= 1000 || score[1] >= 1000
    }
}

data class GameResult(val winningScore: Int, val losingScore: Int, val diceRolls: Int)

interface Dice {
    val rolls: Int
    fun roll(): Int
}

class DeterministicDice : Dice {
    override var rolls: Int = 0
        private set

    override fun roll(): Int {
        return ++rolls
    }
}
