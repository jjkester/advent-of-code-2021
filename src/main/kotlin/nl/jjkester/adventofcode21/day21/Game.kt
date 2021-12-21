package nl.jjkester.adventofcode21.day21

class Game(player1Start: Int, player2Start: Int, private val dice: Dice, val winningScore: Int) {

    var gameStates = mapOf(GameState.initial(player1Start, player2Start) to 1L)
        private set
    var player = Player.One
        private set

    fun play(): GameResult {
        while (!hasWinner()) {
            turn()
        }

        val winningGameState = gameStates.keys.maxByOrNull { game -> game.maxOf { it.score } }!!

        return GameResult(winningGameState.maxOf { it.score }, winningGameState.minOf { it.score }, dice.rolls)
    }

    fun turn() {
        val positionOptions = dice.roll()
            .flatMap { first ->
                dice.roll().flatMap { second ->
                    dice.roll().map { third -> first + second + third }
                }
            }

        val (endedGameStates, activeGameStates) = gameStates.entries.partition { (gameState, _) ->
            gameState.any { it.score >= winningScore }
        }

        val newGameStates = endedGameStates.asSequence()
            .map { it.toPair() }
            .toMap()
            .toMutableMap()

        activeGameStates.asSequence()
            .flatMap { (gameState, count) ->
                positionOptions.map { positions ->
                    gameState.move(player, positions) to count
                }
            }
            .forEach { (gameState, count) ->
                newGameStates.merge(gameState, count, Long::plus)
            }

        gameStates = newGameStates

        changePlayer()
    }

    private fun changePlayer() {
        player = when (player) {
            Player.One -> Player.Two
            Player.Two -> Player.One
        }
    }

    private fun hasWinner(): Boolean {
        return gameStates.keys.all { state ->
            state.any { it.score >= winningScore }
        }
    }
}

enum class Player {
    One,
    Two
}

data class PlayerState(val player: Player, val position: Int, val score: Int) {
    init {
        require(position in 1..10) { "Invalid position: $position" }
        require(score >= 0) { "Invalid score: $score" }
    }

    fun move(positions: Int): PlayerState {
        val newPosition = (((position - 1) + positions) % 10) + 1
        return copy(position = newPosition, score = score + newPosition)
    }
}

data class GameState(val player1: PlayerState, val player2: PlayerState) : Iterable<PlayerState> {
    fun move(player: Player, positions: Int): GameState {
        return when (player) {
            Player.One -> copy(player1 = player1.move(positions))
            Player.Two -> copy(player2 = player2.move(positions))
        }
    }

    companion object {
        fun initial(player1Start: Int, player2Start: Int): GameState {
            return GameState(PlayerState(Player.One, player1Start, 0), PlayerState(Player.Two, player2Start, 0))
        }
    }

    override fun iterator() = iterator {
        yield(player1)
        yield(player2)
    }
}

data class GameResult(val winningScore: Int, val losingScore: Int, val diceRolls: Int)

interface Dice {
    val rolls: Int
    fun roll(): List<Int>
}

class DeterministicDice : Dice {
    override var rolls: Int = 0
        private set

    override fun roll(): List<Int> {
        return listOf(++rolls)
    }
}

class DiracDice : Dice {
    override var rolls: Int = 0
        private set

    override fun roll(): List<Int> {
        rolls++
        return outcomes
    }

    companion object {
        private val outcomes = listOf(1, 2, 3)
    }
}
