package nl.jjkester.adventofcode21.day20

import nl.jjkester.adventofcode21.boilerplate.*

object Day20 : Day {
    override val builder = day(20) {
        val algorithm by input("input.txt") {
            sectionSeparated().first().string().asAlgorithm()
        }
        val input by input("input.txt") {
            sectionSeparated().last().lineSeparated().notBlank().strings().asImage()
        }

        part {
            answer("Number of lit pixels after enhancing twice") {
                reallyEnhance(input, algorithm).size
            }
        }
        part {
            answer("Number of lit pixels after enhancing 50 times") {
                reallyReallyEnhance(input, algorithm).size
            }
        }
    }

    fun reallyReallyEnhance(input: Set<Pair<Int, Int>>, algorithm: List<Boolean>): Set<Pair<Int, Int>> {
        return enhanceRepeatedly(input, algorithm, 50)
    }

    fun reallyEnhance(input: Set<Pair<Int, Int>>, algorithm: List<Boolean>): Set<Pair<Int, Int>> {
        return enhanceRepeatedly(input, algorithm, 2)
    }

    private fun enhanceRepeatedly(input: Set<Pair<Int, Int>>, algorithm: List<Boolean>, times: Int): Set<Pair<Int, Int>> {
        return (1..times)
            .fold(input to false) { previous, _ ->
                enhanceInternal(previous.first, algorithm, previous.second)
            }
            .first
    }

    fun enhance(input: Set<Pair<Int, Int>>, algorithm: List<Boolean>): Set<Pair<Int, Int>> {
        return enhanceInternal(input, algorithm).first
    }

    private fun enhanceInternal(
        input: Set<Pair<Int, Int>>,
        algorithm: List<Boolean>,
        infinite: Boolean = false
    ): Pair<Set<Pair<Int, Int>>, Boolean> {
        val inputArea = input.getArea()

        val newImage = input.getEnhancementArea()
            .mapNotNull { coordinate ->
                val lookupKey = coordinate.area
                    .map {
                        if (it in inputArea) {
                            it in input
                        } else {
                            infinite
                        }
                    }
                    .toInt()

                if (algorithm[lookupKey]) {
                    coordinate
                } else {
                    null
                }
            }
            .toSet()

        val invertBackground =
            if ((!infinite && algorithm.first()) || (infinite && !algorithm.last())) !infinite else infinite

        return newImage to invertBackground
    }

    fun List<String>.asImage(topLeft: Pair<Int, Int> = 0 to 0) = this
        .flatMapIndexed { y, row ->
            row.mapIndexedNotNull { x, char ->
                if (char.isSwitchedOn()) {
                    x + topLeft.first to y + topLeft.second
                } else {
                    null
                }
            }
        }
        .toSet()

    fun String.asAlgorithm() = map { it.isSwitchedOn() }

    private fun Char.isSwitchedOn() = when (this) {
        '.' -> false
        '#' -> true
        else -> throw IllegalStateException("Illegal character: $this")
    }

    private fun Set<Pair<Int, Int>>.getArea(): List<Pair<Int, Int>> {
        return if (isEmpty()) {
            emptyList()
        } else {
            area(minOf { it.first }, maxOf { it.first }, minOf { it.second }, maxOf { it.second })
        }
    }

    private fun Set<Pair<Int, Int>>.getEnhancementArea(): List<Pair<Int, Int>> {
        val minX = minOfOrNull { it.first } ?: 0
        val maxX = maxOfOrNull { it.first } ?: 0
        val minY = minOfOrNull { it.second } ?: 0
        val maxY = maxOfOrNull { it.second } ?: 0

        return area(minX - 2, maxX + 2, minY - 2, maxY + 2)
    }

    private fun area(minX: Int, maxX: Int, minY: Int, maxY: Int) = (minX..maxX).flatMap { x ->
        (minY..maxY).map { y ->
            x to y
        }
    }

    val Set<Pair<Int, Int>>.display
        get() = getArea().groupBy { it.second }
            .toSortedMap()
            .map { it.value }
            .joinToString(System.lineSeparator()) { row ->
                row.map { if (it in this) '#' else '.' }
                    .joinToString("")
            }

    private val Pair<Int, Int>.area
        get() = (second - 1..second + 1).flatMap { y ->
            (first - 1..first + 1).map { x ->
                x to y
            }
        }

    private fun List<Boolean>.toInt(): Int {
        require(size == 9)
        return joinToString("") { if (it) "1" else "0" }.toInt(2)
    }
}
