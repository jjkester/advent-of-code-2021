package nl.jjkester.adventofcode21.day04

data class Bingo(val draws: List<Int>, val cards: List<BingoCard>) {
    fun play(): List<BingoResult> {
        val cardsPlaying = cards.toMutableList()
        val results = mutableListOf<BingoResult>()

        draws.forEachIndexed { turn, draw ->
            cardsPlaying.forEach { it.registerDraw(draw) }
            cardsPlaying.filter { it.completedRowOrColumn }
                .map { BingoResult(it, turn + 1, draw) }
                .forEach {
                    cardsPlaying.remove(it.card)
                    results.add(it)
                }
        }

        return results.toList()
    }
}

data class BingoResult(val card: BingoCard, val turn: Int, val winningDraw: Int) {
    val score get() = card.score * winningDraw
}

data class BingoCard(private val numbers: List<List<BingoCardPosition>>) {
    init {
        require(numbers.map { it.size }.distinct().size == 1) { "A bingo card must have equal length columns" }
        require(numbers.size == numbers[0].size) { "A bingo card must have equal rows and columns" }
        require(
            numbers.flatten().distinctBy { it.number }.size == numbers.flatten().size
        ) { "A bingo card must have all unique numbers" }
    }

    val completedRow
        get() = numbers.any { row -> row.all { it.drawn } }

    val completedColumn
        get() = numbers.flatMap { row -> row.mapIndexed { i, it -> i to it } }
            .groupBy { it.first }
            .values
            .map { col -> col.map { it.second } }
            .any { col -> col.all { it.drawn } }

    val completedRowOrColumn
        get() = completedRow || completedColumn

    val score
        get() = if (completedRowOrColumn) {
            numbers.flatten()
                .filterNot { it.drawn }
                .sumOf { it.number }
        } else {
            0
        }

    fun registerDraw(number: Int) {
        numbers.flatten().find { it.number == number }?.markDrawn()
    }
}

data class BingoCardPosition(val number: Int) {
    var drawn = false
        private set

    fun markDrawn() {
        drawn = true
    }
}
